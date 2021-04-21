package com.single.board.register.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.single.board.common.SHA256Util;

@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private RegisterDAO registerDao;

	@Override
	public int userIdChk(String userid) {
		return registerDao.userIdChk(userid);
	}

	@Override
	public int userRegister(RegisterVO registerVo) {
		
		
		String salt=SHA256Util.generateSalt();
		registerVo.setSalt(salt);
		String password=SHA256Util.getEncrypt(registerVo.getUserpw(), salt);
		
		registerVo.setUserpw(password);
		
		return registerDao.userRegister(registerVo);
	}
}
