package com.goodee.cash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.cash.service.IQnAService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QnARestController {
	
	@Autowired
	private IQnAService qnaService;
	
	// 문의 리스트 조회
	@GetMapping("/qna/getQnAList")
	public Map<String, Object> getQnAList(@RequestParam Map<String, Object> qnaListMap){

	Map<String, Object> getQnAListResult = qnaService.getQnAList(qnaListMap);
	log.debug(getQnAListResult + "<-- NoticeRestController getQnAListResult");
	
	return getQnAListResult;
	}
}
