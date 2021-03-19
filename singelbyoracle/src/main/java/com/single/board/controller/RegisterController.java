package com.single.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.java.Log;

@Controller
@Log
public class RegisterController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public Object register() {
		log.info("회원가입 화면");
		
		return "user/register";
	}
	
	
}
