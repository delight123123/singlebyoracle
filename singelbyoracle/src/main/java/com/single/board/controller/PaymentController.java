package com.single.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.single.board.common.PaginationInfo;
import com.single.board.common.Utility;
import com.single.board.login.model.LoginService;
import com.single.board.model.ReboardVO;
import com.single.board.payment.model.PaymentService;
import com.single.board.payment.model.PaymentVO;
import com.single.board.register.model.RegisterVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
@Transactional
@Controller
public class PaymentController {
	private final static Logger logger=LoggerFactory.getLogger(PaymentController.class);
	
	private IamportClient api=new IamportClient("7198968356913425", "FhiBT3DEezu9djtNYqUiU7PRU2k8Cl4iDmUxpybE8GNlZh9YH3gBa6t48NcL9h4MDYk3xwHFcu0bM99t");
	
	@Autowired
	private LoginService loginServier;
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping("/paymemtSystem")
	public Object paymentPage(HttpSession session,Model model) {
		logger.info("결제시스템 페이지 보이기");
		
		String userid=(String) session.getAttribute("userid");
		
		logger.info("검색할 userid={}",userid);
		
		RegisterVO vo=loginServier.userInfoByuserid(userid);
		
		logger.info("유저 검색 결과 vo={}",vo);
		
		model.addAttribute("vo", vo);
		
		return "payment/payment";
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(
			Model model
			, Locale locale
			, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
	{	
		logger.info("파라미터 imp_uid={}",imp_uid);
		
			return api.paymentByImpUid(imp_uid);
	}
	
	@RequestMapping("/paymentList")
	public Object paymentList(@ModelAttribute PaymentVO paymentVo, Model model,HttpSession session) {
		logger.info("결제내역보이기");
		
		String userid=(String) session.getAttribute("userid");
		paymentVo.setUserid(userid);
		logger.info("파라미터 paymentVo={}",paymentVo);
		
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(paymentVo.getCurrentPage());
		
		paymentVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		paymentVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		logger.info("셋팅후 paymentVo={}",paymentVo);
		
		List<Map<String, Object>> list=paymentService.paymentSearch(paymentVo);
		logger.info("검색결과 list.size()={}",list.size());
		
		int totalRecord=paymentService.paymentSearchTotal(paymentVo);
		logger.info("totalRecord={}",totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("pagingInfo={}",pagingInfo);
		
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		
		return "payment/paymentList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"/paymentInsert/{impuid}/{ordername}/{price}"})
	public int PaymentListInsert(@PathVariable(value= "impuid") String impuid
			,@PathVariable(value = "ordername") String ordername
			,@PathVariable(value = "price") int price, HttpSession session) {
		int res=0;
		String userid=(String) session.getAttribute("userid");
		
		
		logger.info("결제 완료 db insert 파라미터 impuid={},ordername={},price={}",impuid,ordername,price);
		
		PaymentVO vo=new PaymentVO();
		
		vo.setImpUid(impuid);
		vo.setUserid(userid);
		vo.setOrdername(ordername);
		vo.setPrice(price);
		
		logger.info("insert 전 셋팅한 파라미터 vo={}",vo);
		
		res=paymentService.paymentInsert(vo);
		
		logger.info("insert 결과 res={}",res);
		
		return res;
	}
	
}
