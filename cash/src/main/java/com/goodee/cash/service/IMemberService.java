package com.goodee.cash.service;

import com.goodee.cash.vo.Member;

public interface IMemberService {
	
	// 아이디 중복검사
	int memberIdCheck(String memberId);
	
	// 회원가입
	int addMember(Member member);
	
	// 회원정보 상세 조회
	Member getMemberOne(String memberId);
	
	// 회원정보 수정
	int modifyMember(Member member);
	
	// 비밀번호 변경
	int modifyPw(Member member);
	
	// 회원탈퇴
	int cancelMember(String memberId);
	
	// 아이디, 비밀번호 일치 확인
	int idPwConfirm(Member member);

}
