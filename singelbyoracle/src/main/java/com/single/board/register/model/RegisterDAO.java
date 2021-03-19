package com.single.board.register.model;

public interface RegisterDAO {
	int userIdChk(String userid);
	int userRegister(RegisterVO registerVo);
}
