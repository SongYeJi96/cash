package com.goodee.cash.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.cash.vo.Cashbook;
import com.goodee.cash.vo.Hashtag;

@Mapper
public interface HashtagMapper {
	
	// 해당 월의 해시태그 조회
	List<Map<String, Object>> hashTagListMonth(Map<String, Object> paramMap);
	
	// 가계부 내역 별 해시태그 수 조회
	int hashTagCnt(int cashbookNo);
	
	// 해시태그 입력
	void addHashtag(Hashtag hashtag);
	
	// 해시태그 삭제
	void removeHashtag(int cashbookNo);
	
	// 검색 조건 별 해시태그 리스트 행의 수
	int hashTagListCnt(Map<String, Object> hashTagListMap);
	
	// 검색 조건 별 해시태그 리스트 조회
	List<Cashbook> getHashTagList(Map<String, Object> hashTagListMap);

}
