package com.single.board.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class PaymentController {
	private final static Logger logger=LoggerFactory.getLogger(PaymentController.class);
	
	private IamportClient api=new IamportClient("7198968356913425", "FhiBT3DEezu9djtNYqUiU7PRU2k8Cl4iDmUxpybE8GNlZh9YH3gBa6t48NcL9h4MDYk3xwHFcu0bM99t");
	
	
	@RequestMapping("/paymemtSystem")
	public Object paymentPage() {
		logger.info("결제시스템 페이지 보이기");
		
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
			return api.paymentByImpUid(imp_uid);
	}
	
	@RequestMapping("/paymentList")
	public Object paymentList() {
		logger.info("결제내역보이기");
		
		
		return "payment/paymentList";
	}
	
}
