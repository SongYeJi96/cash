package com.goodee.cash.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	
	@PostMapping("/login")
	public String login(HttpSession session,
			@RequestParam(name = "memberId") String memberId,
			@RequestParam(name = "memberPw") String memberPw) {
		
		// service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		
		// session  유효성 검사(로그인 성공)
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
