package com.goodee.cash.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.goodee.cash.vo.Cashbook;

@Mapper
public interface CashMapper {
	
	// 월별 입출금 내역 조회
	List<Cashbook> selectCashbookListByMonth(Map<String, Object> paramMap);
	
	// 일별 입출금 내역 조회
	List<Cashbook> selectCashbookListByDate(Map<String, Object> cashMap);
	
	// 입출금 상세내역 조회
	Cashbook selectCashbookOne(int cashbookNo);
	
	// 입출금 내역 등록
	int addCashbook(Cashbook cashbook);
	
	// 입출금 내역 수정
	int modifyCashbook(Cashbook cashbook);
	
	// 입출금 내역 삭제
	int removeCashbook(int cashbookNo);
	
	// 해당월 수입, 지출 합계, 잔액 조회
	Map<String, Object> totalIncomeAndExpense(Map<String, Object> paramMap);
	
	// 최초 가계부 작성 년도 조회
	String getCashCreatedate(String memberId);
	
	// 월 별 수입/지출 통계 조회
	List<Map<String, Object>> getIncomeAndExpenseData(Map<String, Object> paramMap);
}
