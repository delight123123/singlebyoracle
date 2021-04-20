package com.single.board.payment.model;

import java.sql.Timestamp;

import com.single.board.common.SearchVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO extends SearchVO{
	private int paymentNo;
	private String ordername;
	private String impUid;
	private int price;
	private Timestamp payment_reg;
	private String userid;
}
