package com.single.board.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReboardDAOMybatis implements ReboardDAO{
	String namespace="com.mybatis.mapper.reBoard.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int reboardWrite(ReboardVO reboardVo) {
		return sqlSession.insert(namespace+"reboardWrite", reboardVo);
	}

	@Override
	public List<ReboardVO> reboardSearch(ReboardVO reboardVo) {
		return sqlSession.selectList(namespace+"reboardSearch", reboardVo);
	}

	@Override
	public int searchTotal(ReboardVO reboardVo) {
		return sqlSession.selectOne(namespace+"searchTotal", reboardVo);
	}

	@Override
	public int readcountUp(int reNo) {
		return sqlSession.update(namespace+"readcountUp", reNo);
	}

	@Override
	public ReboardVO reboardSelByNo(int reboardNo) {
		return sqlSession.selectOne(namespace+"reboardSelByNo", reboardNo);
	}

	@Override
	public int reboardEdit(ReboardVO reboardVo) {
		return sqlSession.update(namespace+"reboardEdit", reboardVo);
	}

	@Override
	public int updateSortNo(ReboardVO reboardVo) {
		return sqlSession.update(namespace+"updateSortNo",reboardVo);
	}

	@Override
	public int reply(ReboardVO reboardVo) {
		return sqlSession.insert(namespace+"reply",reboardVo);
	}

	@Override
	public int reboardDel(Map<String, String> map) {
		return sqlSession.delete(namespace+"reboardDel", map);
	}

	@Override
	public int downCntUp(int reboardNo) {
		return sqlSession.update(namespace+"downCntUp", reboardNo);
	}

	@Override
	public int reboardFileCnt(int reboardNo) {
		return sqlSession.selectOne(namespace+"reboardFileCnt", reboardNo);
	}

	@Override
	public List<UpfileListVO> fileByReboardNo(int reboardNo) {
		return sqlSession.selectList(namespace+"fileByReboardNo", reboardNo);
	}
}
