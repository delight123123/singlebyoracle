package com.single.board.refund.model;

import java.sql.Timestamp;

import com.single.board.common.SearchVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundVO extends SearchVO{
	private int refundNo;
	private String refundType;
	private int refundPrice;
	private Timestamp reportingDate;
	private String refundState;
	private Timestamp refundDate;
	private int paymentNo;
}
