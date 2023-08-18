package com.goodee.cash.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.goodee.cash.vo.Cashbook;

@Mapper
public interface CashMapper {
	
	// 월별 입출금 내역 조회
	List<Cashbook> selectCashbookListByMonth(Map<String, Object> cListByMonthMap);
	
	// 일별 입출금 내역 조회
	List<Cashbook> selectCashbookListByDate(Map<String, Object> cLisByDatetMap);
	
	// 입출금 상세내역 조회
	Cashbook selectCashbookOne(int cashbookNo);
	
	// 입출금 내역 등록
	int addCashbook(Cashbook cashbook);
	
	// 입출금 내역 수정
	int modifyCashbook(Cashbook cashbook);
	
	// 입출금 내역 삭제
	int removeCashbook(Cashbook cashbook);

	
}
