package com.goodee.cash.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.HashDocAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.CashMapper;
import com.goodee.cash.mapper.HashtagMapper;
import com.goodee.cash.vo.Cashbook;
import com.goodee.cash.vo.Hashtag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CashService implements ICashService{
	@Autowired
	private CashMapper cashMapper;
	@Autowired
	private HashtagMapper hashTagMapper;
	
	// 달력, 월별 입출금 내역 조회
	public Map<String, Object> getCalendar(Map<String, Object> cashMap){
		
		// 값 저장
		String memberId = (String) cashMap.get("memberId");
		String targetYearStr = (String) cashMap.get("targetYear");
		String targetMonthStr = (String) cashMap.get("targetMonth");
		Integer targetYear = null;
		Integer targetMonth = null;
		
		// 오늘 날짜
		Calendar currentDate = Calendar.getInstance();
		
		// 출력하고자하는 년도와 월이 매개값으로 넘어 오면
		if(targetYearStr != null && targetMonthStr != null) {
			
			targetYear = Integer.parseInt(targetYearStr);
			targetMonth = Integer.parseInt(targetMonthStr);
			
			// API에서 자동으로 Calendar.YEAR 값으로 12가 입력되면 월 1, 년 +1
			// API에서 자동으로 Calendar.MONTH 값으로 -1이 입력되면 월 12, 년 -1
			currentDate.set(Calendar.YEAR, targetYear);
			currentDate.set(Calendar.MONTH, targetMonth);
			
		} else { // 값이 없을 경우
			
			// 현재 년, 월 값 저장
			targetYear = currentDate.get(Calendar.YEAR);
			targetMonth = currentDate.get(Calendar.MONTH);
		}
		
		// API가 적용된 targetYear, targetMonth 값 저장
        targetYear = currentDate.get(Calendar.YEAR);
        targetMonth = currentDate.get(Calendar.MONTH);
		
		// 마지막 날짜
		int lastDate = currentDate.getActualMaximum(Calendar.DATE);
		
		// 1일의 요일을 이용하여 출력할 시작 공백 수 -> 요일 맵핑 수 - 1
		int beginBlank = currentDate.get(Calendar.DAY_OF_WEEK) -1;
		log.debug("beginBlank: "+beginBlank);
		
		// 마지막 날짜 이후 출력할 공백 수
		int endBlank = 0;
		if(beginBlank + lastDate + endBlank % 7 !=0) {
			endBlank = 7 - ((beginBlank + lastDate) % 7);
		}
		// 전체 Td 수
		int totalTd = beginBlank + lastDate + endBlank;
		
		// mapper에 전달 값 Map에 저장
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("targetYear", targetYear);
		paramMap.put("targetMonth", targetMonth +1);
		
		// 월별 입출금 내역 리스트
		List<Cashbook> cashbookListByMonth = cashMapper.selectCashbookListByMonth(paramMap);
		
		// 해당 월의 해시태그 리스트
		List<Map<String, Object>> hashTagList = hashTagMapper.hashTagListMonth(paramMap);
		
		// 해당 월의 수입, 지출 금액 합계, 잔액 조회
		Map<String, Object> totalIncomeAndExpense = cashMapper.totalIncomeAndExpense(paramMap);
		
		// 수입, 지출, 잔액 원 단위로 형식 변환
		// 원 단위로 형식화할 Locale 설정 (한국어)
        Locale koreanLocale = new Locale("ko", "KR");
        
        // NumberFormat 객체 생성
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(koreanLocale);
        
        BigDecimal totalIncomeValue = (BigDecimal) totalIncomeAndExpense.get("totalIncome");
        BigDecimal totalExpenseValue = (BigDecimal) totalIncomeAndExpense.get("totalExpense");
        BigDecimal balanceValue = (BigDecimal) totalIncomeAndExpense.get("balance");

        String totalIncome = numberFormat.format(totalIncomeValue.intValue());
        String totalExpense = numberFormat.format(totalExpenseValue.intValue());
        String balance = numberFormat.format(balanceValue.intValue());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("targetYear", targetYear);
		resultMap.put("targetMonth", targetMonth);
		resultMap.put("lastDate", lastDate);
		resultMap.put("beginBlank", beginBlank);
		resultMap.put("endBlank", endBlank);
		resultMap.put("totalTd", totalTd);
		resultMap.put("cashbookList", cashbookListByMonth);
		resultMap.put("hashTagList", hashTagList);
		resultMap.put("totalIncome", totalIncome);
		resultMap.put("totalExpense", totalExpense);
		resultMap.put("balance", balance);
		
		log.debug("CashService.getCalendar() resultMap:" + resultMap.toString());
		
		return resultMap;
	}
	
	// 일별 입출금 내역 조회
	public Map<String, Object> selectCahsbookListByDate(Map<String, Object> cashMap){
		
		log.debug("CashService.cashbookListByDate() cashMap:" + cashMap.toString());
		
		// 년, 월, 일 값 저장
		String cashbookDate = (String) cashMap.get("cashbookDate");
		String targetYear;
		Integer targetMonth;
		Integer day;
		
		// cashbookDate 값 여부에 따른 분기
		if(cashbookDate == null) {
			
			targetYear = (String) cashMap.get("targetYear");
		    targetMonth = Integer.parseInt((String) cashMap.get("targetMonth"));
		    day = Integer.parseInt((String) cashMap.get("day"));
			
		} else {
			targetYear = cashbookDate.substring(0,4);
			targetMonth	= Integer.parseInt(cashbookDate.substring(5,7)); 
			day = Integer.parseInt(cashbookDate.substring(8));
		}
		
		// 1의 자리 월과 일 앞에 0을 붙여줌
		String strM = String.format("%02d", targetMonth);
		String strD = String.format("%02d", day);
		
		// cashbookDate
		cashbookDate = String.format("%s-%s-%s", targetYear, strM, strD);
		
		// cashMap에 cashbookDate 값 저장
		cashMap.put("cashbookDate", cashbookDate);
		
		List<Cashbook> cashbookListByDate = cashMapper.selectCashbookListByDate(cashMap);
		log.debug("CashService.cashbookListByDate() resultMap:" + cashbookListByDate.toString());
		
		// 리턴 값 Map에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("cashbookListByDate", cashbookListByDate);
		resultMap.put("cashbookDate", cashbookDate);
		resultMap.put("targetYear", targetYear);
		resultMap.put("targetMonth", targetMonth);
		resultMap.put("day", day);
		
		return resultMap;
	}

	// 가계부 등록
	public int addCashbook(Cashbook cashbook) {
		
		int addCashbookRow = cashMapper.addCashbook(cashbook);
		log.debug(addCashbookRow + "<-- CashService.java addCashbook addCashbookRow");
		
		// addCashbookRow 값에 따른 분기
		if(addCashbookRow == 0) { // 등록 실패
			return 0;
			
		} else { // 등록 성공
			
			// 가계부 등록 성공 -> 해시태그 있음 -> 해시태그 추출 -> 해시태그 입력(반복)
			int cashbookNo = cashbook.getCashbookNo(); // cashbookNo 값 저장
			String memo = cashbook.getMemo(); // memo 값 저장
			String memo2 = memo.replace("#", " #"); //  "#" -> " #", #문자 앞 공백 추가 후 저장
			
			// 중복된 해시태그방지를 위해 set자료구조를 사용
			Set<String> set = new HashSet<String>();
			
			for(String ht : memo2.split(" ")) {
				if(ht.startsWith("#")) {
				String ht2 = ht.replace("#", "");
					if(ht.length() > 0) {
						set.add(ht2); // set은 중복된 값은 add되지 않는다
						log.debug(set +"<-- CashService.java addCashbook set");
					}
				}
			}
			
			for(String s : set) {
	    		Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(s);
				hashTagMapper.addHashtag(hashtag);
				log.debug(hashtag +"<-- CashService.java addCashbook hasgtag");
			}
		}
		
		return addCashbookRow;
	}

	// 가계부 상세 조회
	public Cashbook cashbookOne(int cashbook) {
		
		Cashbook cashbookOne = cashMapper.selectCashbookOne(cashbook);
		log.debug("CashService.cashbookListByDate() cashbookOne:" + cashbookOne.toString());
		
		return cashbookOne;
	}

	// 가계부 수정
	public int modifyCashbook(Cashbook cashbook) {
		
		log.debug("CashService.modifyCashbook() cashbook:" + cashbook.toString());
		
		// 값 저장(cashbookNo)
		int cashbookNo = cashbook.getCashbookNo();
		
		int modifyCashbookRow = cashMapper.modifyCashbook(cashbook);
		log.debug("CashService.modifyCashbook() modifyCashbookRow:" + modifyCashbookRow);
		
		// modifyCashbookRow 값에 따른 분기
		if(modifyCashbookRow == 0) { // 수정 실패
			return 0;
			
		} else { // 수정 성공
			
			// 기존 해시태그가 있다면 삭제, 수정된 memo에 해시태그가 있으면 추출하여 등록
			int hashTagCnt = hashTagMapper.hashTagCnt(cashbookNo); // 해시태그 유무 확인
			
			if(hashTagCnt != 0) {
				hashTagMapper.removeHashtag(cashbookNo); // 해시태그 삭제
			}
			
			String memo = cashbook.getMemo(); // memo 값 저장
			String memo2 = memo.replace("#", " #"); //  "#" -> " #", #문자 앞 공백 추가 후 저장
			
			// 중복된 해시태그방지를 위해 set자료구조를 사용
			Set<String> set = new HashSet<String>();
			
			for(String ht : memo2.split(" ")) {
				if(ht.startsWith("#")) {
				String ht2 = ht.replace("#", "");
					if(ht.length() > 0) {
						set.add(ht2); // set은 중복된 값은 add되지 않는다
						log.debug(set +"<-- CashService.java addCashbook set");
					}
				}
			}
			
			for(String s : set) {
	    		Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(s);
				hashTagMapper.addHashtag(hashtag);
				log.debug(hashtag +"<-- CashService.java addCashbook hasgtag");
			}	
		}
		
		return modifyCashbookRow;
	}

	// 가계부 삭제
	public int removeCashbook(int cashbookNo) {
		
		int removeCashbookRow = cashMapper.removeCashbook(cashbookNo);
		log.debug("CashService.removeCashbook() removeCashbookRow:" + removeCashbookRow);
		
		return removeCashbookRow;
	}

	// 해시태그 리스트 조회
	public Map<String, Object> getHashTagList(Map<String, Object> hashTagListMap) {
		
		// 반환값1(검색 조건 별 행의 수)
		int hashTagListCnt = hashTagMapper.hashTagListCnt(hashTagListMap);
		log.debug("CashService.getHashTagList() hashTagListCnt:" + hashTagListCnt);
		
		// 페이징
		int currentPage = Integer.parseInt((String) hashTagListMap.get("currentPage")); // 현재 페이지
		int rowPerPage = Integer.parseInt((String) hashTagListMap.get("rowPerPage")); // 페이지당 행의 수
		
		// 시작 행번호
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 마지막 페이지
		int lastPage = hashTagListCnt / rowPerPage;
		if(hashTagListCnt % rowPerPage !=0) {
			lastPage +=1;
		}
		
		// 페이지 블럭
		int currentblock = 0; // 현제 페이지 블럭(currentPage / pageLength)
		int pageLength = 10; // 현제 페이지 블럭의 들어갈 페이지 수
		if(currentPage % pageLength == 0) {
			currentblock = currentPage / pageLength;
		} else {
			currentblock = (currentPage / pageLength) +1;
		}
		
		int startPage = (currentblock -1) * pageLength +1; // 블럭의 시작페이지
		int endPage = startPage + pageLength -1; // 블럭의 마지막 페이지
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		
		// hashTagListMap에 값 저장
		hashTagListMap.put("beginRow", beginRow);
		hashTagListMap.put("rowPerPage", rowPerPage);
		
		// 반환값2(검색 조건 별 해시태그 리스트)
		List<Cashbook> getHashTagList = hashTagMapper.getHashTagList(hashTagListMap);
		log.debug("CashService.getHashTagList() getHashTagList:" + getHashTagList.toString());
		
		// 반환값 Map에 저장
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("getHashTagList", getHashTagList);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("pageLength", pageLength);
		resultMap.put("currentPage", currentPage);
		
		return resultMap;
	}

	// 최초 가계부 작성 년도 조회
	public String getCashCreatedate(String memberId) {
		
		String getCashCreatedate = cashMapper.getCashCreatedate(memberId);
		log.debug("CashService.getCashCreatedate() getCashCreatedate:" + getCashCreatedate);
		
		return getCashCreatedate;
	}

	// 월 별 수입/지출 통계 조회
	public List<Map<String, Object>> getIncomeAndExpenseData(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> resultMap = cashMapper.getIncomeAndExpenseData(paramMap);
		log.debug("CashService.getIncomeAndExpenseData() resultMap:" + resultMap);
		
		return resultMap;
	}
	
}
