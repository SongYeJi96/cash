package com.goodee.cash.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.ICashService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
		
	@Autowired 
		private ICashService cashService;
		
		// home.jsp
		@GetMapping("/home")
		public String cashbook(HttpSession session, Model model,
								@RequestParam Map<String, Object> cashMap) {
			
			log.debug("HomeController.cashbook() cashMap : "+ cashMap.toString());
		
			// 세션에서 로그인 된 memberId 추출
			String memberId = (String) session.getAttribute("loginId");
			
			cashMap.put("memberId", memberId);
			
			// cashbook
			Map<String, Object> cashbookMap = cashService.getCalendar(cashMap);
			
			log.debug("CashController.cashbook() cashbookMap : "+ cashbookMap.toString());
			
			model.addAttribute("targetYear", cashbookMap.get("targetYear"));
			model.addAttribute("targetMonth", cashbookMap.get("targetMonth"));
			model.addAttribute("lastDate", cashbookMap.get("lastDate"));
			model.addAttribute("beginBlank", cashbookMap.get("beginBlank"));
			model.addAttribute("endBlank", cashbookMap.get("endBlank"));
			model.addAttribute("totalTd", cashbookMap.get("totalTd"));
			model.addAttribute("cashbookList", cashbookMap.get("cashbookList"));
			model.addAttribute("hashTagList", cashbookMap.get("hashTagList"));
			model.addAttribute("totalIncome", cashbookMap.get("totalIncome"));
			model.addAttribute("totalExpense", cashbookMap.get("totalExpense"));
			model.addAttribute("balance", cashbookMap.get("balance"));
			
			return "/home";
		}
}
