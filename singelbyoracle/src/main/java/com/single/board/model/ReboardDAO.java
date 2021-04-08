package com.single.board.model;

import java.util.List;
import java.util.Map;

public interface ReboardDAO {
	int reboardWrite(ReboardVO reboardVo);
	List<ReboardVO> reboardSearch(ReboardVO reboardVo);
	int searchTotal(ReboardVO reboardVo);
	int readcountUp(int reNo);
	ReboardVO reboardSelByNo(int reboardNo);
	int reboardEdit(ReboardVO reboardVo);
	int updateSortNo(ReboardVO reboardVo);
	int reply(ReboardVO reboardVo);
	int reboardDel(Map<String, String> map);
	int downCntUp(int reboardNo);
	int reboardFileCnt(int reboardNo);
	List<UpfileListVO> fileByReboardNo(int reboardNo);
	int upfilelistInsert(UpfileListVO upfileListVo);
}
