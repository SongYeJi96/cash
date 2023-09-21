package com.goodee.cash.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.cash.vo.Member;

@Mapper
public interface LoginMapper {
	
	// 로그인 정보 조회
	Member getLoginInfo(Member member);

}
