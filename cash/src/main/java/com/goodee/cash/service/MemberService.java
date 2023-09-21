package com.goodee.cash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.MemberMapper;
import com.goodee.cash.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberMapper memberMapper;

	// 아이디 중복검사
	public int memberIdCheck(String memberId) {
		
		int memberIdCnt = memberMapper.memberIdCheck(memberId);
		log.debug(memberIdCnt + "<-- MemberService memberIdCheck memberIdCnt");
		
		return memberIdCnt;
	}

	// 회원가입
	public int addMember(Member member) {
		
		int addMemberRow = memberMapper.addMember(member);
		log.debug(addMemberRow + "<-- MemberService addMember addMemberRow");
		
		return addMemberRow;
	}

	// 회원정보 상세조회
	public Member getMemberOne(String memberId) {
		
		Member member = memberMapper.getMemberOne(memberId);
		log.debug(member + "<-- MemberService getMemberOne member");
		
		return member;
	}

	// 회원정보 수정
	public int modifyMember(Member member) {
		
		int modifyMemberRow = memberMapper.modifyMember(member);
		log.debug(modifyMemberRow + "<-- MemberService modifyMember modifyMemberRow");
		
		return modifyMemberRow;
	}

	// 비밀번호 변경
	public int modifyPw(Member member) {
		
		int modifyPwRow = memberMapper.modifyPw(member);
		log.debug(modifyPwRow + "<-- MemberService modifyPw modifyPwRow");
		
		return modifyPwRow;
	}

	// 회원탈퇴
	public int cancelMember(String memberId) {
		
		int cancelMemberRow = memberMapper.cancelMember(memberId);
		log.debug(cancelMemberRow + "<-- MemberService cancelMember cancelMemberRow");
		
		return cancelMemberRow;
	}

	// 아이디, 비밀번호 일치 확인
	public int idPwConfirm(Member member) {
		
		int idPwCnt = memberMapper.idPwConfirm(member);
		log.debug(idPwCnt + "<-- MemberService idPwConfirm idPwCnt");
		
		return idPwCnt;
	}
}
