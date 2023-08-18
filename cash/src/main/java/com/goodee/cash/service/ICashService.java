package com.goodee.cash.service;

import java.util.List;
import java.util.Map;

import com.goodee.cash.vo.Cashbook;

public interface ICashService {
	
	// 달력, 월별 입출금 내역 조회
	Map<String, Object> getCalendar(String memberId, Integer targetYear, Integer targetMonth);
	
	// 일별 입출금 내역 조회
	List<Cashbook> selectCahsbookListByDate(String memberId, String cashbookDate);
}
