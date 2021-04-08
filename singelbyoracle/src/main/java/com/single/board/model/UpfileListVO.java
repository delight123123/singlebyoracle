package com.single.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpfileListVO {
	private int fileNo;
	private String fileName;
	private long filesize;
	private int downCount;
	private String originalFileName;
	private int reboardNo;
}
