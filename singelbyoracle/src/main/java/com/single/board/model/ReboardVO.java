package com.single.board.model;

import java.sql.Timestamp;

import com.single.board.common.SearchVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReboardVO extends SearchVO{
	private int reboardNo;
	private String reboardTitle;
	private String reboardContent;
	private Timestamp reboardReg;
	private int readcount;
	private int groupno;
	private int step;
	private int sortno;
	private String delflag;
	private String userid;
}
