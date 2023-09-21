package com.goodee.cash.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/member/*", "/cashbook/*", "/qna/*", "/admin/*"})
public class SessionFilter extends HttpFilter implements Filter {
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("세션 확인 필터");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // 세션 가져오기
        HttpSession session = httpRequest.getSession();
        String memberId = (String) session.getAttribute("loginId");
        
        String msg = "";
        if (memberId == null || memberId.equals("")) {
            msg = URLEncoder.encode("로그인 후 이용 가능합니다.", "UTF-8");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login/loginForm?msg=" + msg);
            return;
        }
        
		chain.doFilter(request, response);
	}
}
