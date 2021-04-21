package com.single.board.refund.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RefundDAOMybatis implements RefundDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace="com.mybatis.mapper.refundSystem.";

	@Override
	public int refundInsert(RefundVO refundVo) {
		return sqlSession.insert(namespace+"refundInsert", refundVo);
	}

	@Override
	public List<Map<String, Object>> refundSearch(RefundVO refundVo) {
		return sqlSession.selectList(namespace+"refundSearch", refundVo);
	}

	@Override
	public int refundSearchTotal(RefundVO refundVo) {
		return sqlSession.selectOne(namespace+"refundSearchTotal", refundVo);
	}

	@Override
	public int refundCancel(int refundNo) {
		return sqlSession.delete(namespace+"refundCancel", refundNo);
	}
	
	
	
	
}
