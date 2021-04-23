package com.single.board.register.model;

import java.util.List;

public interface RegisterDAO {
	int userIdChk(String userid);
	int userRegister(RegisterVO registerVo);
	List<RegisterVO> noneWithdrawal(RegisterVO registerVo);
	int noneWithdrawalTotal(RegisterVO registerVo);
	List<RegisterVO> withdrawal(RegisterVO registerVo);
	int withdrawalTotal(RegisterVO regiserVo);
	int forcedExit(String userid);
	int cancle(String userid);
	List<RegisterVO> userAll();
}
