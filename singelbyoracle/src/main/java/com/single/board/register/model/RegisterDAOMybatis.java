package com.single.board.register.model;

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
	

}
