package com.goodee.cash.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.ICashService;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CashController {
	@Autowired 
	private ICashService cashService;
	
	// msg 변수 초기화
	String msg = "";
	
	// cashbookListByDate.jsp
	@GetMapping("/cashbook/cashbookListByDate")
	public String cashbookListByDate(HttpSession session, Model model,
										@RequestParam Map<String, Object> cashMap) {
		
		log.debug("CashController.cashbookListByDate() cashMap : "+ cashMap.toString());
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		
		cashMap.put("memberId", memberId);
		
		Map<String, Object> resultMap = cashService.selectCahsbookListByDate(cashMap);
		
		model.addAttribute("cListByDate", resultMap.get("cashbookListByDate"));
		model.addAttribute("cashbookDate", resultMap.get("cashbookDate"));
		model.addAttribute("targetYear", resultMap.get("targetYear"));
		model.addAttribute("targetMonth", resultMap.get("targetMonth"));
		model.addAttribute("day", resultMap.get("day"));
		
		return "/cash/cashbookListByDate";
	}
	
	// cashbookOne.jsp
	@GetMapping("/cashbook/cashbookOne")
	public String cashbookOne(Model model, @RequestParam int cashbookNo) {
		
		log.debug("CashController.cashbookOne() cashbookNo : "+ cashbookNo);
		
		// 가계부 상세 조회
		Cashbook cashbookOne = cashService.cashbookOne(cashbookNo);
		
		model.addAttribute("cashbookOne", cashbookOne);
		
		return "/cash/cashbookOne";
	}
	
	// cashbookListByTag.jsp
	@GetMapping("/cashbook/cashbookListByTag")
	public String cashbookListByTag(Model model, @RequestParam String word) {
		
		model.addAttribute("word", word);
		
		return "/cash/cashbookListByTag";
	}
	
	// statistics.jsp
	@GetMapping("/cashbook/statistics")
	public String statistics() {
		return "/cash/statistics";
	}
	
	// 가계부 등록
	@PostMapping("/cashbook/addCashbook")
	public String addCashbook(HttpSession session,
								@ModelAttribute Cashbook cashbook) throws UnsupportedEncodingException {
		
		log.debug("CashController.addCashbook() cashbook : "+ cashbook.toString());
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		
		cashbook.setMemberId(memberId);
		
		int addCashbookRow = cashService.addCashbook(cashbook);
		
		if(addCashbookRow == 0) {
			// 가계부 등록 실패 시 msg
			msg = "가계부 등록 실패"; 
		} else {
			// 가계부 등록 성공 시 msg
			msg = "가계부 내역이 등록되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		// 값 저장(cashbookDate)
		String cashbookDate = cashbook.getCashbookDate();
		
		return "redirect:/cashbook/cashbookListByDate?cashbookDate=" + cashbookDate + "&msg=" + msg;
	}
	
	// 가계부 수정
	@PostMapping("/cashbook/modifyCashbook")
	public String modifyCashbook(@ModelAttribute Cashbook cashbook) throws UnsupportedEncodingException {
	
		log.debug("CashController.modifyCashbook() cashbookNo : "+ cashbook);
		
		// cashbookNo 값 저장
		int cashbookNo = cashbook.getCashbookNo();
		
		// 가계부 수정
		int modifyCashbookRow = cashService.modifyCashbook(cashbook);
		
		if(modifyCashbookRow == 0) {
			// 가계부 수정 실패 시 msg
			msg = "가계부 수정 실패"; 
		} else {
			// 가계부 등록 성공 시 msg
			msg = "가계부 내역이 수정되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/cashbook/cashbookOne?cashbookNo=" + cashbookNo +"&msg=" + msg;
	}
	
	// 가계부 삭제
	@GetMapping("/cashbook/removeCashbook")
	public String removeCashbook(@RequestParam int cashbookNo, @RequestParam String cashbookDate) throws UnsupportedEncodingException {
		
		log.debug("CashController.removeCashbook() cashbookNo : "+ cashbookNo);
		
		// 가계부 삭제
		int removeCashbookRow = cashService.removeCashbook(cashbookNo);
		
		if(removeCashbookRow == 0) {
			// 가계부 삭제 실패 시 msg
			msg = "가계부 삭제 실패"; 
		} else {
			// 가계부 등록 성공 시 msg
			msg = "가계부 내역이 삭제되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/cashbook/cashbookListByDate?cashbookDate=" + cashbookDate +"&msg=" + msg;
	}
}
