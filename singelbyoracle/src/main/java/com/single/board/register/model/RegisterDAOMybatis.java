package com.single.board.register.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDAOMybatis implements RegisterDAO{

	private String namespace="com.mybatis.mapper.register.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int userIdChk(String userid) {
		return sqlSession.selectOne(namespace+"useridChk", userid);
	}

	@Override
	public int userRegister(RegisterVO registerVo) {
		return sqlSession.insert(namespace+"userregister", registerVo);
	}

	@Override
	public List<RegisterVO> noneWithdrawal(RegisterVO registerVo) {
		return sqlSession.selectList(namespace+"noneWithdrawal", registerVo);
	}

	@Override
	public int noneWithdrawalTotal(RegisterVO registerVo) {
		return sqlSession.selectOne(namespace+"noneWithdrawalTotal", registerVo);
	}

	@Override
	public List<RegisterVO> withdrawal(RegisterVO registerVo) {
		return sqlSession.selectList(namespace+"withdrawal", registerVo);
	}

	@Override
	public int withdrawalTotal(RegisterVO regiserVo) {
		return sqlSession.selectOne(namespace+"withdrawalTotal", regiserVo);
	}

	@Override
	public int forcedExit(String userid) {
		return sqlSession.update(namespace+"forcedExit", userid);
	}

	@Override
	public int cancle(String userid) {
		return sqlSession.update(namespace+"cancle", userid);
	}

	@Override
	public List<RegisterVO> userAll() {
		return sqlSession.selectList(namespace+"userAll");
	}
	

}
