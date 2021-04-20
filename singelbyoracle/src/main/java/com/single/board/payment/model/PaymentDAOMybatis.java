package com.single.board.payment.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDAOMybatis implements PaymentDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace="com.mybatis.mapper.paymentSystem.";

	@Override
	public int paymentInsert(PaymentVO paymentVo) {
		return sqlSession.insert(namespace+"paymentInsert", paymentVo);
	}

	@Override
	public List<Map<String, Object>> paymentSearch(PaymentVO paymentVo) {
		return sqlSession.selectList(namespace+"paymentSearch", paymentVo);
	}

	@Override
	public int paymentSearchTotal(PaymentVO paymentVo) {
		return sqlSession.selectOne(namespace+"paymentSearchTotal", paymentVo);
	}
	
	
	
}
