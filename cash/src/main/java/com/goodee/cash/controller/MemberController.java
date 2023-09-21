package com.goodee.cash.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.ILoginService;
import com.goodee.cash.service.IMemberService;
import com.goodee.cash.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private IMemberService memberService;
	
	// msg 변수 초기화
	String msg = "";
	
	// signUp.jsp
	@GetMapping("/signUp")
	public String addMemberForm() {
		
		return "/signUp";
	}
	
	// memberOne.jsp
	@GetMapping("/member/memberOne")
	public String memberOne(Model model, @RequestParam String memberId) {
		
		log.debug(memberId+"<-- MemberController memberOne memberId");
		
		Member member = memberService.getMemberOne(memberId);
		log.debug(member+"<-- MemberController memberOne member");
		
		model.addAttribute("member", member);
		
		return "/member/memberOne";
	}
	
	// cancelMember.jsp
	@GetMapping("/member/cancelMember")
	public String cancelMember() {
		
		return "/member/cancelMember";
	}
	
	// 회원가입
	@PostMapping("/member/addMember")
	public String addMember(@ModelAttribute Member member) throws UnsupportedEncodingException {
		
		log.debug(member+"<-- MemberController addMember member");
		
		int addMemberRow = memberService.addMember(member);
		log.debug(addMemberRow+"<-- MemberController addMember addMemberRow");
		
		// addMemberRow 값의 따른 msg 분기
		if(addMemberRow == 0) {
			// 회원가입 실패 시 msg
			msg = "회원가입 실패"; 
		} else {
			// 회원가입 성공 시 msg
			msg = "회원가입 되었습니다. 로그인 후 이용해주세요."; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/login/loginForm?msg=" + msg;
	}
	
	// 회원정보 수정
	@PostMapping("/member/modifyMember")
	public String modifyMember(@ModelAttribute Member member) throws UnsupportedEncodingException {
		
		log.debug(member+"<-- MemberController modifyMember member");
		
		// memberId 값 저장
		String memberId = member.getMemberId();
		
		int modifyMemberRow = memberService.modifyMember(member);
		log.debug(modifyMemberRow+"<-- MemberController modifyMember modifyMemberRow");
		
		// modifyMemberRow 값의 따른 msg 분기
		if(modifyMemberRow == 0) {
			// 회원정보 수정 실패 시 msg
			msg = "회원정보 수정 실패"; 
		} else {
			// 회원정보 수정 성공 시 msg
			msg = "회원정보가 수정되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/member/memberOne?memberId=" + memberId + "&msg=" + msg;
	}
	
	// 비밀번호 변경
	@PostMapping("/member/modifyPw")
	public String modifyPw(@ModelAttribute Member member) throws UnsupportedEncodingException {
		
		log.debug(member+"<-- MemberController modifyPw member");
		
		// memberId 값 저장
		String memberId = member.getMemberId();
		
		int modifyPwRow = memberService.modifyPw(member);
		log.debug(modifyPwRow+"<-- MemberController modifyPw modifyPwRow");
		
		// modifyPwRow 값의 따른 msg 분기
		if(modifyPwRow == 0) {
			// 비밀번호 변경 실패 시 msg
			msg = "비밀번호 변경 실패"; 
		} else {
			// 비밀번호 변경 성공 시 msg
			msg = "비밀번호가 변경 되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/member/memberOne?memberId="+ memberId + "&msg=" + msg;
	}
	
	// 회원탈퇴(회원상태 비활성화로 변경)
	@PostMapping("/member/cancelMember")
	public String cancelMember(HttpSession session, @RequestParam String memberId) throws UnsupportedEncodingException {
		
		log.debug(memberId+"<-- MemberController cancelMember memberId");
		
		int cancelMemberRow = memberService.cancelMember(memberId);
		log.debug(cancelMemberRow+"<-- MemberController cancelMember cancelMemberRow");
		
		// cancelMemberRow 값의 따른 msg 분기
		if(cancelMemberRow == 0) {
			// 회원탈퇴 실패 시 msg
			msg = "회원탈퇴 실패"; 
		} else {
			// 회원탈퇴 성공 시 msg
			msg = "회원탈되 되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		session.invalidate(); // 세션 종료
		
		return "redirect:/login/loginForm?msg=" + msg;
	}
}
