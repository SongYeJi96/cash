package com.goodee.cash.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.cash.service.ICashService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CashRestController {
	
	@Autowired
	private ICashService cashService;
	
	// 해시태그 목록 조회
	@GetMapping("/cashbook/getHashTagList")
	public Map<String, Object> getHashTagList(HttpSession session, 
												@RequestParam Map<String, Object> hashTagListMap){
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		
		hashTagListMap.put("memberId", memberId);
		
		Map<String, Object> getHashTagListResult = cashService.getHashTagList(hashTagListMap);
		log.debug(getHashTagListResult + "<-- CashRestController.getHashTagList() getHashTagListResult");
		
		return getHashTagListResult;
	}
	
	// 최초 가계부 작성 년도 조회
	@GetMapping("/cashbook/getCashCreatedate")
	public String getCashCreatedate(HttpSession session) {
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		
		String getCashCreatedate = cashService.getCashCreatedate(memberId);
		log.debug(getCashCreatedate+"<-- CashRestController.getCashCreatedate() getCashCreatedate");
		
		return getCashCreatedate;
	}
	
	// 월 별 수입/지출 통계 조회
	@GetMapping("/cashbook/getIncomeAndExpenseData")
	public List<Map<String, Object>> getIncomeAndExpenseData(HttpSession session,@RequestParam String year){
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		
		// 서비스에 보낼 값 map에 저장
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId);
		paramMap.put("year", year);
		
		// 월 별 수입/지출 통계 조회
		List<Map<String, Object>> resultMap = cashService.getIncomeAndExpenseData(paramMap);
		log.debug(resultMap + "<-- CashRestController.getIncomeAndExpenseData() resultMap");
		
		return resultMap;
	}
}
