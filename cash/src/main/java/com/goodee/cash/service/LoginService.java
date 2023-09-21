package com.goodee.cash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.LoginMapper;
import com.goodee.cash.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService implements ILoginService {

	@Autowired
	private LoginMapper loginMapper;

	// 로그인
	public Member getLoginInfo(Member member) {
		
		log.debug("LoginService.getLoginInfo() member:" + member);
		
		Member loginInfo = loginMapper.getLoginInfo(member);
		log.debug("LoginService.getLoginInfo() loginInfo:" + loginInfo);
		
		return loginInfo;
	}
}
