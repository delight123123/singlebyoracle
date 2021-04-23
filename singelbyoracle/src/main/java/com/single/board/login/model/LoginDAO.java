package com.single.board.login.model;

import com.single.board.register.model.RegisterVO;

public interface LoginDAO {
	String saltByuserid(String userid);
	String pwByuserid(String userid);
	RegisterVO userInfoByuserid(String userid);
	int userPwCg(RegisterVO registerVo);
}
