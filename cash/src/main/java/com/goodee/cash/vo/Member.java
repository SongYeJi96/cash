package com.goodee.cash.vo;

import lombok.Data;

@Data
public class Member {
	
	private String memberId ;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private int idLevel;
	private String createdate;
	private String updatedate;
}
