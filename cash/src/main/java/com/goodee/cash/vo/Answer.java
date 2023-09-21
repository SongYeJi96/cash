package com.goodee.cash.vo;

import lombok.Data;

@Data
public class Answer {
	
	private int answerNo;
	private int questionNo;
	private String memberId;
	private String answerContent;
	private String createdate;
	private String updatedate;

}
