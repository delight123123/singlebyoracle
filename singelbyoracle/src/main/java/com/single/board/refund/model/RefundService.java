package com.single.board.refund.model;

import java.util.List;
import java.util.Map;

public interface RefundService {
	int refundInsert(RefundVO refundVo);
	List<Map<String, Object>> refundSearch(RefundVO refundVo);
	int refundSearchTotal(RefundVO refundVo);
	int refundCancel(int refundNo);
}
