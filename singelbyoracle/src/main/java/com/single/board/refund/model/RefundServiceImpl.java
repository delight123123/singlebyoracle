package com.single.board.refund.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements RefundService{

	@Autowired
	private RefundDAO refundDao;

	@Override
	public int refundInsert(RefundVO refundVo) {
		return refundDao.refundInsert(refundVo);
	}

	@Override
	public List<Map<String, Object>> refundSearch(RefundVO refundVo) {
		return refundDao.refundSearch(refundVo);
	}

	@Override
	public int refundSearchTotal(RefundVO refundVo) {
		return refundDao.refundSearchTotal(refundVo);
	}

	@Override
	public int refundCancel(int refundNo) {
		return refundDao.refundCancel(refundNo);
	}
}
