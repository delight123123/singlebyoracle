package com.single.board.register.model;

public interface RegisterService {
	int userIdChk(String userid);
	int userRegister(RegisterVO registerVo);
}
