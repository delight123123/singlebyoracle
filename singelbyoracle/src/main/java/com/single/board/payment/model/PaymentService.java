package com.single.board.payment.model;

import java.util.List;
import java.util.Map;

public interface PaymentService {
	int paymentInsert(PaymentVO paymentVo);
	List<Map<String, Object>> paymentSearch(PaymentVO paymentVo);
	int paymentSearchTotal(PaymentVO paymentVo);
	PaymentVO paymentSelByimp(String impUid);
	PaymentVO paymentSelByNo(int paymentNo);
}
