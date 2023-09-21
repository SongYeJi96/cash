package com.goodee.cash.service;

import java.util.*;

import com.goodee.cash.vo.Cashbook;

public interface ICashService {
	
	// 달력, 월별 입출금 내역 조회
	Map<String, Object> getCalendar(Map<String, Object> cashMap);
	
	// 일별 입출금 내역 조회
	Map<String, Object> selectCahsbookListByDate(Map<String, Object> cashMap);
	
	// 가계부 등록
	int addCashbook(Cashbook cashbook);
	
	// 가계부 상세 조회
	Cashbook cashbookOne(int cashbookNo);
	
	// 가계부 수정
	int modifyCashbook(Cashbook cashbook);
	
	// 가계부 삭제
	int removeCashbook(int cashbookNo);
	
	// 해시태그 리스트 조회
	Map<String, Object> getHashTagList(Map<String, Object> hashTagListMap);
	
	// 최초 가계부 작성 년도 조회
	String getCashCreatedate(String memberId);
	
	// 월 별 수입/지출 통계 조회
	List<Map<String, Object>> getIncomeAndExpenseData(Map<String, Object> paramMap);
}
