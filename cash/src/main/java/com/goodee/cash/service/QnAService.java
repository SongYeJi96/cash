package com.goodee.cash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.QnAMapper;
import com.goodee.cash.vo.Answer;
import com.goodee.cash.vo.Cashbook;
import com.goodee.cash.vo.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QnAService implements IQnAService {
	
	@Autowired
	private QnAMapper qnaMapper;
	
	// 문의 리스트 조회
	public Map<String, Object> getQnAList(Map<String, Object> qnaListMap) {
		
		// 반환값1(검색 조건 별 행의 수)
		int noticeListCnt = qnaMapper.getQnAListCnt(qnaListMap);
		log.debug("QnAService.getQnAList() noticeListCnt:" + noticeListCnt);
		
		// 페이징
		int currentPage = Integer.parseInt((String) qnaListMap.get("currentPage")); // 현재 페이지
		int rowPerPage = Integer.parseInt((String) qnaListMap.get("rowPerPage")); // 페이지당 행의 수
		
		// 시작 행번호
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 마지막 페이지
		int lastPage = noticeListCnt / rowPerPage;
		if(noticeListCnt % rowPerPage !=0) {
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
		
		// qnaListMap에 값 저장
		qnaListMap.put("beginRow", beginRow);
		qnaListMap.put("rowPerPage", rowPerPage);
		
		// 반환값2(검색 조건 별 해시태그 리스트)
		List<Map<String, Object>> getQnAList = qnaMapper.getQnAList(qnaListMap);
		log.debug("QnAService.getQnAList() getQnAList:" + getQnAList.toString());
		
		// 반환값 Map에 저장
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("getQnAList", getQnAList);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("pageLength", pageLength);
		resultMap.put("currentPage", currentPage);
		
		return resultMap;
	
	}

	// 문의 등록
	public int addQuestion(Question question) {
		
		int addQuestionRow = qnaMapper.addQuestion(question);
		log.debug("QnAService.addQuestion() addQuestionRow:" + addQuestionRow);
		
		return addQuestionRow;
	}

	// 문의 상세 조회
	public Map<String, Object> qnaOne(int questionNo) {
		
		// 문의 상세 조회
		Map<String, Object> question = qnaMapper.qnaOne(questionNo);
		
		return question;
	}

	// 문의 수정
	public int modifyQuestion(Question question) {
		
		int modifyQuestionRow = qnaMapper.modifyQuestion(question);
		log.debug("QnAService.modifyQuestion() modifyQuestionRow:" + modifyQuestionRow);
		
		return modifyQuestionRow;
	}

	// 문의 삭제
	public int removeQuestion(int questionNo) {
		
		int removeQuestionRow = qnaMapper.removeQuestion(questionNo);
		log.debug("QnAService.removeQuestion() removeQuestionRow:" + removeQuestionRow);
		
		return removeQuestionRow;
	}

	// 문의답변 등록
	public int addAnswer(Answer answer) {
		
		int addAnswerRow = qnaMapper.addAnswer(answer);
		log.debug("QnAService.addAnswer() addAnswerRow:" + addAnswerRow);
		
		return addAnswerRow;
	}

	// 문의답변 조회
	public List<Answer> answerList(int questionNo) {
		
		List<Answer> answerList = qnaMapper.answerList(questionNo);
		log.debug("QnAService.addAnswer() answerList:" + answerList.toString());
		
		return answerList;
	}

	// 문의답변 수정
	public int modifyAnswer(Answer answer) {
		
		int modifyAnswerRow = qnaMapper.modifyAnswer(answer);
		log.debug("QnAService.addAnswer() modifyAnswerRow:" + modifyAnswerRow);
		
		return modifyAnswerRow;
	}

	// 문의답변 삭제
	public int removeAnswer(int answerNo) {
		
		int removeAnswerRow = qnaMapper.removeAnswer(answerNo);
		log.debug("QnAService.addAnswer() removeAnswerRow:" + removeAnswerRow);
		
		return removeAnswerRow;
	}

}
