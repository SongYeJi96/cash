package com.goodee.cash.vo;

import lombok.Data;

@Data
public class Question {
	
	private int questionNo;
	private String questionTitle;
	private String questionContent;
	private String questionWriter;
	private String createdate;
	private String updatedate;
}
