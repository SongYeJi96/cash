package com.goodee.cash.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// login.jsp
	@GetMapping("/login/loginForm")
	public String login(HttpServletRequest request) {
		
		// 쿠키에 loginId 있는지 확인 후 값 request 속성에 저장
		Cookie[] cookies = request.getCookies();
		String loginId = null; // loginId 변수 초기화
		
		if (cookies != null) {
	        for (Cookie c : cookies) {
	            if ("loginId".equals(c.getName())) {
	                loginId = c.getValue();
	                break; // 원하는 쿠키를 찾았으므로 루프 종료
	            }
	        }
	    }

	    // loginId를 request 속성에 저장
	    if (loginId != null) {
	        request.setAttribute("loginId", loginId);
	    }
		
		return "/login/loginForm";
	}
	
	// 로그아웃
	@GetMapping("/login/logout")
	public String logout(HttpSession session) {
		
		session.invalidate(); // 세션종료
		
		return "redirect:/home";
	}
	
}
