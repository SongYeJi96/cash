package com.goodee.cash.vo;

import lombok.Data;

@Data
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String createdate;
	private String updatedate;
}
