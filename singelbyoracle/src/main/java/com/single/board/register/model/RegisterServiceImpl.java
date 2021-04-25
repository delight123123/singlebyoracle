package com.single.board.register.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public List<RegisterVO> noneWithdrawal(RegisterVO registerVo) {
		return registerDao.noneWithdrawal(registerVo);
	}

	@Override
	public int noneWithdrawalTotal(RegisterVO registerVo) {
		return registerDao.noneWithdrawalTotal(registerVo);
	}

	@Override
	public List<RegisterVO> withdrawal(RegisterVO registerVo) {
		return registerDao.withdrawal(registerVo);
	}

	@Override
	public int withdrawalTotal(RegisterVO regiserVo) {
		return registerDao.withdrawalTotal(regiserVo);
	}

	@Override
	public int forcedExit(String userid) {
		return registerDao.forcedExit(userid);
	}

	@Override
	public int cancle(String userid) {
		return registerDao.cancle(userid);
	}

	@Override
	public List<RegisterVO> userAll() {
		return registerDao.userAll();
	}

	@Transactional
	@Override
	public int multiCancle(List<RegisterVO> listVo) {
		int cnt=0;
		try {
			for(RegisterVO vo: listVo) {
				String userid=vo.getUserid();
				if(userid!=null && !userid.isEmpty()) {
					cnt=registerDao.cancle(userid);
				}
			}	
		}catch(RuntimeException e) {
			e.printStackTrace();
			cnt=-1;
		}
		
		
		return cnt;

	}

	@Transactional
	@Override
	public int multiforcedExit(List<RegisterVO> listVo) {
		int cnt=0;
		try {
			for(RegisterVO vo:listVo) {
				String userid=vo.getUserid();
				if(userid!=null && !userid.isEmpty()) {
					System.out.println(userid);
					cnt=registerDao.forcedExit(userid);
				}
			}
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			cnt=-1;
		}
		
		
		return cnt;
	}
}
