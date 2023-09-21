package com.goodee.cash.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.cash.service.ILoginService;
import com.goodee.cash.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginRestController {

	@Autowired
	private ILoginService loginService;
	
	// 로그인
	@PostMapping("/login/getLoginInfo")
	public String login(HttpSession session,
						HttpServletResponse response,
						@RequestParam(name = "memberId") String memberId,
						@RequestParam(name = "memberPw") String memberPw,
						@RequestParam(name = "idSave", required = false) String idSave) throws UnsupportedEncodingException {
		
		log.debug(idSave+"<-- idSave");
		
		// service에 전달 값 저장
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		Member memberInfo = loginService.getLoginInfo(member);
		
		// memberInfo 값에 따른 분기
		if(memberInfo != null) { // 로그인 성공
			
			// 값 저장
			String loginId = memberInfo.getMemberId(); // 로그인 아이디
			int loginIdLevel = memberInfo.getIdLevel(); // 로그인 아이디 레벨
			
			// 세션에 값 저장
			session.setAttribute("loginId", loginId);
			session.setAttribute("loginIdLevel", loginIdLevel);
			
			// idSave 체크박스가 선택되었을 경우, 사용자 아디이들 쿠키에 저장
			if(idSave != null && idSave.equals("Y")) {
				Cookie loginIdCookie = new Cookie("loginId", String.valueOf(loginId));
				loginIdCookie.setMaxAge(30 * 24 * 60 * 60); // 쿠키 유효 기간 설정 (30일)
				response.addCookie(loginIdCookie);
				
			} else { // idSave 값이 null일 경우
				Cookie loginIdCookie = new Cookie("loginId", "");
				loginIdCookie.setMaxAge(0); // 쿠키 즉시 삭제
			    response.addCookie(loginIdCookie);
			}
			
		} else { // 로그인 실패
			return "false";
		}
		
		return "success";
	}
}
