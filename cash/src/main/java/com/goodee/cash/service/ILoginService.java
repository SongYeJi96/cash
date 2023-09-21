package com.goodee.cash.service;

import com.goodee.cash.vo.Member;

public interface ILoginService {
	
	// 로그인
	Member getLoginInfo(Member member);

}
