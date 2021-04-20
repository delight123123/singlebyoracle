package com.single.board.payment.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentDAO paymentDao;

	@Override
	public int paymentInsert(PaymentVO paymentVo) {
		return paymentDao.paymentInsert(paymentVo);
	}

	@Override
	public List<Map<String, Object>> paymentSearch(PaymentVO paymentVo) {
		return paymentDao.paymentSearch(paymentVo);
	}

	@Override
	public int paymentSearchTotal(PaymentVO paymentVo) {
		return paymentDao.paymentSearchTotal(paymentVo);
	}
	
	
	
}
