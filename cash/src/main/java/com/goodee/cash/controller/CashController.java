package com.goodee.cash.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.ICashService;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CashController {
	@Autowired 
	private ICashService cashService;
	
	@GetMapping("/cash/cashbook")
	public String cashbook(HttpSession session, Model model,
							@RequestParam(required = false, name = "targetYear") Integer targetYear,
							@RequestParam(required = false, name = "targetMonth") Integer targetMonth) {
		
		log.debug("CashController.calendar() param targetYear : "+ targetYear);
		log.debug("CashController.calendar() param targetMonth : "+ targetMonth);
		
		// 세션에서 로그인 된 memberId 추출
		// session.getAttribute("loginMember")
		String memberId = "user1";
		
		// cashbook
		Map<String, Object> resultMap = cashService.getCalendar(memberId, targetYear, targetMonth);
		
		model.addAttribute("targetYear", resultMap.get("targetYear"));
		model.addAttribute("targetMonth", resultMap.get("targetMonth"));
		model.addAttribute("lastDate", resultMap.get("lastDate"));
		model.addAttribute("beginBlank", resultMap.get("beginBlank"));
		model.addAttribute("endBlank", resultMap.get("endBlank"));
		model.addAttribute("totalTd", resultMap.get("totalTd"));
		model.addAttribute("cashbookList", resultMap.get("cashbookList"));
		
		log.debug("CashController.calendar() resultMap : "+ resultMap.toString());
		return "/cash/cashbook";
	}
	
	@GetMapping("/cash/cashbookListByDate")
	public String cashbookListByDate(HttpSession session, Model model,
			@RequestParam(required = false, name = "cashbookDate") String cashbookDate) {
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = "user1";
		
		
		List<Cashbook> cListByDate = cashService.selectCahsbookListByDate(memberId, cashbookDate);
		model.addAttribute("cListByDate", cListByDate);
		
		return "/cash/cashbookListByDate";
	}
}
