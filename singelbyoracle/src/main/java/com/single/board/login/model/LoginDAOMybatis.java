package com.single.board.login.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.single.board.register.model.RegisterVO;

@Repository
public class LoginDAOMybatis implements LoginDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace="com.mybatis.mapper.userlogin.";
	
	@Override
	public String saltByuserid(String userid) {
		return sqlSession.selectOne(namespace+"saltByuserid", userid);
	}

	@Override
	public RegisterVO userInfoByuserid(String userid) {
		return sqlSession.selectOne(namespace+"userInfoByuserid", userid);
	}

	@Override
	public String pwByuserid(String userid) {
		return sqlSession.selectOne(namespace+"pwByuserid", userid);
	}

	@Override
	public int userPwCg(RegisterVO registerVo) {
		return sqlSession.update(namespace+"userPwCg", registerVo);
	}
}
