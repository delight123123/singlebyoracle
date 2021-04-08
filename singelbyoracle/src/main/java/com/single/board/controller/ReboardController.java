package com.single.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.single.board.common.FileUploadUtil;
import com.single.board.model.ReboardDAO;
import com.single.board.model.ReboardService;
import com.single.board.model.ReboardVO;
import com.single.board.model.UpfileListVO;

@Controller
public class ReboardController {

	private final static Logger logger=LoggerFactory.getLogger(ReboardController.class);
	
	@Autowired
	private ReboardService reboardService;
	
	@Autowired
	private FileUploadUtil fileuploadUtil;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET )
	public Object write() {
		
		logger.info("글쓰기 화면 보이기");
		
		
		return "reboard/write";

	}
	
	@ResponseBody
	@RequestMapping("/boardWrite")
	public int boardWrite(@ModelAttribute ReboardVO vo) {
		int res=0;
		
		res=reboardService.reboardWrite(vo);
		
		if(res!=0) {
			logger.info("게시글 등록 성공");

		}
		
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/fileuplod")
	public ResponseEntity<?> fileupload(@RequestParam("upfile") MultipartFile[] uploadfiles
			, @RequestParam("res") int res
			, HttpSession session
			,HttpServletRequest request) {
		
		List<UpfileListVO> list=fileuploadUtil.fileupload(uploadfiles, request, session);
		
		for(int i=0; i<list.size();i++) {
			list.get(i).setReboardNo(res);
		}
		
		//파일 업로드 테이블에 insert
		
		return new ResponseEntity("업로드 성공  ", HttpStatus.OK);
	}
	
	@RequestMapping("/readCnt")
	public Object readCnt(@RequestParam("reNo") int reNo) {
		
		logger.info("detail전 조회수 증가 파라미터 reboardNo={}",reNo);

		int res=reboardService.readcountUp(reNo);

		logger.info("조회수 증가 결과 res={}",res);
		
		return "redirect:/detail?reboardNo="+reNo;
	}
	
	@RequestMapping("/detail")
	public Object detail(@RequestParam("reboardNo") int reboardNo, Model model ) {
		logger.info("게시글 상세보기 파라미터 reboardNo={}",reboardNo);
		
		ReboardVO vo=reboardService.reboardSelByNo(reboardNo);
		
		//해당 게시글에 대한 업로드한 파일
		int res=reboardService.reboardFileCnt(reboardNo);
		logger.info("해당 게시글 업로드 파일 수 res={}",res);
		if(res>0) {
			
			List<UpfileListVO> list=reboardService.fileByReboardNo(reboardNo);
			logger.info("게시글 업로드파일들 list={}",list);
			model.addAttribute("upfilelist", list);
		}
		
		logger.info("상세 보기 검색 결과 vo={}",vo);

		model.addAttribute("vo", vo);
		
		return "reboard/detail";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Object edit(@RequestParam("no") int reboardNo) {
		logger.info("게시글 수정 파라미터 reboardNo={}",reboardNo);
		
		
		
		return "reboard/edit";
	}
	
}
