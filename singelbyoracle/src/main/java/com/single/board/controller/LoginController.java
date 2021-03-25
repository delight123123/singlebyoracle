package com.single.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.single.board.login.model.LoginService;
import com.single.board.register.model.RegisterVO;


@Controller
public class LoginController {
	
	private Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Object login() {
		logger.info("로그인 화면 띄우기");
		
		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestParam String userid,@RequestParam String userpw
			,@RequestParam(required = false) String chkSave
			,HttpServletRequest request,HttpServletResponse response
			,Model model) {
		
		logger.info("로그인 파라미터 userid={}, userpw={}",userid,userpw);
		
		int res=loginService.userLogin(userid, userpw);
		
		logger.info("결과 res={}",res);
		String url="",msg="";
		
		if(res==LoginService.NONE_USERID) {
			msg="아이디가 존재하지 않습니다.";
			url="/login";
		}else if(res==LoginService.DISAGREE_PWD) {
			msg="비밀번호가 틀립니다.";
			url="/login";
		}else if(res==LoginService.LOGIN_OK) {
			RegisterVO registerVo=loginService.userInfoByuserid(userid);
			
			logger.info("로그인 유저 정보={}",registerVo);
			
			HttpSession session=request.getSession();
			session.setAttribute("userid", registerVo.getUserid());
			
			Cookie ck=new Cookie("chk_userid", userid);
			ck.setPath("/");
			
			if(chkSave!=null) {
				ck.setMaxAge(1000*24*60*60);
				response.addCookie(ck);
			}else {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			msg="로그인 완료! 환영합니다!";
			url="/main";
		}else {
			msg="로그인 오류";
			url="/login";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("logout")
	public Object logout(HttpSession session) {
		String userid= (String) session.getAttribute("userid");
		
		logger.info("로그아웃 해당 아이디 userid={}",userid);
		
		session.removeAttribute("userid");
		
		return "redirect:/login";
	}
}
