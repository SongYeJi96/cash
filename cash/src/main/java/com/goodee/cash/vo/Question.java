package com.goodee.cash.vo;

import lombok.Data;

@Data
public class Question {
	
	private int questionNo;
	private String memberId;
	private String questionTitle;
	private String questionContent;
	private String createdate;
	private String updatedate;
}
