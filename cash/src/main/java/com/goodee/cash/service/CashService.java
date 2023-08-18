package com.goodee.cash.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.CashMapper;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CashService implements ICashService{
	@Autowired
	private CashMapper cashMapper;
	
	// 달력, 월별 입출금 내역 조회
	// 요청단에서 target(y, m) 넘어오지 않으면 기본값 -1
	public Map<String, Object> getCalendar(String memberId, Integer targetYear, Integer targetMonth){ // null 값으로 받으려 Integer
		
		Calendar firstDay = Calendar.getInstance();
		
		// 첫번째 날짜
		firstDay.set(Calendar.DATE, 1);
		
		// 원하는 년/월이 요청 매개값으로 넘어 왔다면
		if(targetYear != null && targetMonth != null) {
			firstDay.set(Calendar.YEAR, targetYear);
			// API애서 자동 분기 12입력 -> 0, 년은 +1, -1이 입력되면 11이 되고 년 -1
			firstDay.set(Calendar.MONTH, targetMonth); 
		}
		
		// 바뀐 년도와 월 정보를 다시 셋팅
        targetYear = firstDay.get(Calendar.YEAR);
        targetMonth = firstDay.get(Calendar.MONTH);
		
		// 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		
		// 1일의 요일을 이용하여 출력할 시작 공백 수 -> 요일 맵핑 수 - 1
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) -1;
		log.debug("beginBlank: "+beginBlank);
		
		// 마지막 날짜 이후 출력할 공백 수
		int endBlank = 0;
		if(beginBlank + lastDate + endBlank % 7 !=0) {
			endBlank = 7 - ((beginBlank + lastDate) % 7);
		}
		// 전체 Td 수
		int totalTd = beginBlank + lastDate + endBlank;
		
		// 월별 입출금 내역 리스트
		Map<String, Object> cashbookListByMonthMap = new HashMap<String, Object>();
		cashbookListByMonthMap.put("memberId", memberId);
		cashbookListByMonthMap.put("targetYear", targetYear);
		cashbookListByMonthMap.put("targetMonth", targetMonth);
		
		List<Cashbook> cashbookListByMonth = cashMapper.selectCashbookListByMonth(cashbookListByMonthMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("targetYear", targetYear);
		resultMap.put("targetMonth", targetMonth);
		resultMap.put("lastDate", lastDate);
		resultMap.put("beginBlank", beginBlank);
		resultMap.put("endBlank", endBlank);
		resultMap.put("totalTd", totalTd);
		resultMap.put("cashbookList", cashbookListByMonth);
		
		log.debug("CashService.getCalendar() resultMap:" + resultMap.toString());
		
		return resultMap;
	}
	
	// 일별 입출금 내역 조회
	public List<Cashbook> selectCahsbookListByDate(String memberId, String cashbookDate){
		
		Map<String, Object> cashbookLisByDatetMap = new HashMap<String, Object>();
		cashbookLisByDatetMap.put("memberId", memberId);
		cashbookLisByDatetMap.put("cashbookDate", cashbookDate);
		
		List<Cashbook> cashbookListByDate = cashMapper.selectCashbookListByMonth(cashbookLisByDatetMap);
		
		return cashbookListByDate;
	}
	
}
