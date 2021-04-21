package com.single.board.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.single.board.common.PaginationInfo;
import com.single.board.common.Utility;
import com.single.board.refund.model.RefundService;
import com.single.board.refund.model.RefundVO;

@Controller
public class RefundController {

	private final static Logger logger=LoggerFactory.getLogger(RefundController.class);
	
	@Autowired
	private RefundService refundService;
	
	@RequestMapping("/refundList")
	public Object refundList(@ModelAttribute RefundVO refundvo,Model model) {
		logger.info("환불신청 리스트 파라미터 refundvo={}",refundvo);
		
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(refundvo.getCurrentPage());
		
		refundvo.setRecordCountPerPage(Utility.RECORD_COUNT);
		refundvo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		logger.info("셋팅후 paymentVo={}",refundvo);
		
		List<Map<String, Object>> list=refundService.refundSearch(refundvo);
		logger.info("검색결과 list.size()={}",list.size());
		
		int totalRecord=refundService.refundSearchTotal(refundvo);
		logger.info("totalRecord={}",totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("pagingInfo={}",pagingInfo);
		
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "payment/refundList";
	}
}
