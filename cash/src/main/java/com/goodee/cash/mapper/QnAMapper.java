package com.goodee.cash.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.cash.vo.Answer;
import com.goodee.cash.vo.Cashbook;
import com.goodee.cash.vo.Question;

@Mapper
public interface QnAMapper {
	
	// 검색 별 문의 리스트 행의 수
	int getQnAListCnt(Map<String, Object> qnaListMap);
	
	// 검색 별 문의 리스트
	List<Map<String, Object>> getQnAList(Map<String, Object> qnaListMap);
	
	// 문의 작성
	int addQuestion(Question question);
	
	// 문의 상세 조회
	Map<String, Object> qnaOne(int questionNo);
	
	// 문의 수정
	int modifyQuestion(Question question);
	
	// 문의 삭제
	int removeQuestion(int questionNo);
	
	// 문의답변 등록
	int addAnswer(Answer answer);
	
	// 문의답변 조회
	List<Answer> answerList(int questionNo);
	
	// 문의답변 수정
	int modifyAnswer(Answer answer);
	
	// 문의답변 삭제
	int removeAnswer(int answerNo);

}
