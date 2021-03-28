package com.single.board.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReboardServiceImpl implements ReboardService{

	@Autowired
	private ReboardDAO reboardDao;
	
	@Override
	public int reboardWrite(ReboardVO reboardVo) {
		return reboardDao.reboardWrite(reboardVo);
	}

	@Override
	public List<ReboardVO> reboardSearch(ReboardVO reboardVo) {
		return reboardDao.reboardSearch(reboardVo);
	}

	@Override
	public int searchTotal(ReboardVO reboardVo) {
		return reboardDao.searchTotal(reboardVo);
	}

	@Override
	public int readcountUp(int reNo) {
		return reboardDao.readcountUp(reNo);
	}

	@Override
	public ReboardVO reboardSelByNo(int reboardNo) {
		return reboardDao.reboardSelByNo(reboardNo);
	}

	@Override
	public int reboardEdit(ReboardVO reboardVo) {
		return reboardDao.reboardEdit(reboardVo);
	}

	@Override
	@Transactional
	public int reply(ReboardVO reboardVo) {
		
		int cnt=reboardDao.updateSortNo(reboardVo);
		
		cnt=reboardDao.reply(reboardVo);
		
		return cnt;
	}

	@Override
	public int reboardDel(Map<String, String> map) {
		return reboardDao.reboardDel(map);
	}

	@Override
	public int downCntUp(int reboardNo) {
		return reboardDao.downCntUp(reboardNo);
	}
	
}
