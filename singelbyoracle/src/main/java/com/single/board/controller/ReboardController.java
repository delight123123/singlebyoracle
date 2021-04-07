package com.single.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReboardController {

	private final static Logger logger=LoggerFactory.getLogger(ReboardController.class);
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET , RequestMethod.POST})
	public Object write() {
		
		logger.info("글쓰기 화면 보이기");
		
		
		return "reboard/write";
	}
	
}
