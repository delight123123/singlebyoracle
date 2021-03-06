package com.single.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.single.board.common.ExcelFileCreatUtil;
import com.single.board.common.PaginationInfo;
import com.single.board.common.Utility;
import com.single.board.register.model.MemberListVO;
import com.single.board.register.model.RegisterService;
import com.single.board.register.model.RegisterVO;

@Controller
public class UserManagementController {

	private static final Logger logger=LoggerFactory.getLogger(UserManagementController.class);
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private ExcelFileCreatUtil excelFileCreatUtil;
	
	@RequestMapping("/userManagement")
	public Object userManagement(@ModelAttribute RegisterVO registerVo, Model model
			,HttpSession session) {
		logger.info("회원 관리 페이지 파라미터 registerVo={}",registerVo);
		String userid=(String) session.getAttribute("userid");
		registerVo.setUserid(userid);
		//비탈퇴 회원
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.ANNBLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(registerVo.getCurrentPage());
		registerVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		registerVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		int noneTotal=registerService.noneWithdrawalTotal(registerVo);
		pagingInfo.setTotalRecord(noneTotal);
		logger.info("비탈퇴 total={}",noneTotal);
		logger.info("비탈퇴 세팅 memberVo={}",registerVo);
		List<RegisterVO> list=registerService.noneWithdrawal(registerVo);
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		//탈퇴회원
		PaginationInfo pagingInfo2=new PaginationInfo();
		pagingInfo2.setBlockSize(Utility.ANNBLOCK_SIZE);
		pagingInfo2.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo2.setCurrentPage(registerVo.getCurrentPage2());
		
		registerVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		registerVo.setFirstRecordIndex2(pagingInfo2.getFirstRecordIndex());
		
		int total=registerService.withdrawalTotal(registerVo);
		
		pagingInfo2.setTotalRecord(total);
		logger.info("탈퇴회원 total={}",total);
		logger.info("탈퇴회원 세팅 memberVo={}",registerVo);
		
		List<RegisterVO> list2=registerService.withdrawal(registerVo);
		
		model.addAttribute("list2", list2);
		model.addAttribute("pagingInfo2", pagingInfo2);
		
		
		return "userManagement/userManagement";
	}
	
	@RequestMapping("/userManagementForcedExit")
	@ResponseBody
	public int forcedExit(@RequestParam String userid) {
		logger.info("회원 강퇴 파라미터 userid={}",userid);
		
		int res=registerService.forcedExit(userid);
		
		return res;
	}
	
	@RequestMapping("/userManagementCancle")
	@ResponseBody
	public int cancle(@RequestParam String userid) {
		logger.info("회원 탈퇴 취소 파라미터 userid={}",userid);
		
		int res=registerService.cancle(userid);
		
		return res;
	}
	
	@RequestMapping("/userManagementMultiforcedExit")
	public String multiforcedExit(@ModelAttribute MemberListVO memberListVo,
			Model model) {
		logger.info("멀티 강퇴 파라미터 memberListVo={}",memberListVo);
		
		List<RegisterVO> list=memberListVo.getMemberList();
		
		int res=registerService.multiforcedExit(list);
		String msg="강퇴 처리중 오류 발생", url="/userManagement";
		
		if(res>0) {
			msg="선택한 회원을 강퇴 처리 하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/userManagementMultiCancle")
	public String multiCancle(@ModelAttribute MemberListVO memberListVo,
			Model model) {
		logger.info("멀티 탈퇴 취소 파라미터 memberListVo={}",memberListVo);
		
		List<RegisterVO> list=memberListVo.getMemberList();
		
		int res=registerService.multiCancle(list);
		
		String msg="탈퇴 취소 중 오류 발생", url="/userManagement";
		if(res>0) {
			msg="선택한 회원을 탈퇴 취소하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/userListExcelDown")
	public ModelAndView userListExcelDown(HttpSession session) {
		String auth=(String) session.getAttribute("auth");
		logger.info("회원 목록 다운로드 권한 auth={}",auth);
		List<RegisterVO> list=registerService.userAll();
		
		logger.info("회원 목록 회원 수 list.size()={}",list.size());
		
		SXSSFWorkbook workbook=excelFileCreatUtil.makeExcelDown(list);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("locale", Locale.KOREA);
		map.put("workbook", workbook);
		map.put("workbookName", "회원목록");
		map.put("auth", auth);
		
		ModelAndView mav=new ModelAndView("excelDownloadView", map);
        
        return mav;
		
	}
}
