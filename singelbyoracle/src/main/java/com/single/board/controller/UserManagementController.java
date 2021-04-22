package com.single.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagementController {

	private static final Logger logger=LoggerFactory.getLogger(UserManagementController.class);
	
	@RequestMapping("/userManagement")
	public Object userManagement() {
		logger.info("회원 관리 페이지");
		
		return "userManagement/userManagement";
	}
}
