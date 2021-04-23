package com.single.board.login.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.single.board.common.SHA256Util;
import com.single.board.register.model.RegisterVO;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDAO loginDao;

	@Override
	public int userLogin(String userid, String userpw) {
		System.out.println(userid);
		String salt=loginDao.saltByuserid(userid);
		String pwByuserid=loginDao.pwByuserid(userid);
		System.out.println(salt);
		int result=0;
		
		if(salt==null||salt.isEmpty()) {
			result=NONE_USERID;
		}else {
			String pw=SHA256Util.getEncrypt(userpw, salt);
			System.out.println("pw="+pw);
			if(pw.equals(pwByuserid)) {
				result=LOGIN_OK;
			}else {
				result=DISAGREE_PWD;
			}
		}
		
		return result;
	}

	@Override
	public RegisterVO userInfoByuserid(String userid) {
		return loginDao.userInfoByuserid(userid);
	}

	@Override
	public int userPwCg(RegisterVO registerVo) {
		
		String salt=loginDao.saltByuserid(registerVo.getUserid());
		String pw=SHA256Util.getEncrypt(registerVo.getUserpw(), salt);
		registerVo.setUserpw(pw);
		
		return loginDao.userPwCg(registerVo);
	}


}
