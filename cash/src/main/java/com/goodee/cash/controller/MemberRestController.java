package com.goodee.cash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.cash.service.IMemberService;
import com.goodee.cash.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberRestController {
	
	@Autowired
	private IMemberService memberService;
	
	// 아이디 중복검사
	@PostMapping("/member/idCheck")
	public String idCheck(@RequestParam String memberId) {
		
		log.debug(memberId +"<-- MemberRestController idCheck memberId");
		
		// 아이디 중복검사
		int memberIdCnt = memberService.memberIdCheck(memberId);
		log.debug(memberIdCnt +"<-- MemberRestController idCheck memberIdCnt");
		
		// memberCheck 값에 따른 리턴 값 분기
		if(memberIdCnt == 0) { // 값이 없을 경우
			return "false";
		} else {
			return "true"; // 값이 있을 경우
		}	
	}
	
	// 아이디, 비밀번호 일치 확인
	@PostMapping("/member/idPwConfirm")
	public String idPwConfirm(@ModelAttribute Member member) {
		
		log.debug(member.toString() +"<-- MemberRestController idPwConfirm member");
		
		// 아이디 중복검사
		int idPwCnt = memberService.idPwConfirm(member);
		log.debug(idPwCnt +"<-- MemberRestController idPwConfirm idPwCnt");
		
		// idPwCnt 값에 따른 리턴 값 분기
		if(idPwCnt == 0) { // 값이 없을 경우
			return "false";
		} else {
			return "true"; // 값이 있을 경우
		}
	}
}
