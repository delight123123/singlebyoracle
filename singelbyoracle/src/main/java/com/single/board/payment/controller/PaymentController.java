package com.single.board.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {
	private final static Logger logger=LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping("/paymemtSystem")
	public Object paymentPage() {
		logger.info("결제시스템 페이지 보이기");
		
		return "payment/payment";
	}
	
}
