package com.goodee.cash.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.IQnAService;
import com.goodee.cash.vo.Answer;
import com.goodee.cash.vo.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QnAController {
	
	@Autowired
	private IQnAService qnaService;
	
	// msg 변수 초기화
	String msg = "";
	
	// qnaList.jsp
	@GetMapping("/qna/qnaList")
	public String qnaList() {
		return "/qna/qnaList";
	}
	
	// addQuestion.jsp
	@GetMapping("/qna/addQuestionForm")
	public String addQuestionForm() {
		return "/qna/addQuestion";
	}
	
	// qnaOne.jsp
	@GetMapping("/qna/qnaOne")
	public String qnaOne(Model model, @RequestParam int questionNo) {
		
		// 문의 상세 조회
		Map<String, Object> question = qnaService.qnaOne(questionNo);
		
		// 답변 조회
		List<Answer> answerList = qnaService.answerList(questionNo);
		
		model.addAttribute("question", question);
		model.addAttribute("answerList", answerList);
		
		return "/qna/qnaOne";
	}
	
	// adminQnAList.jsp
	@GetMapping("/admin/adminQnAList")
	public String adminQnaList() {
		return "/admin/adminQnAList";
	}
	
	// adminQnAOne.jsp
	@GetMapping("/admin/adminQnAOne")
	public String adminQnAOne(Model model, @RequestParam int questionNo) {
		
		// 문의 상세 조회
		Map<String, Object> question = qnaService.qnaOne(questionNo);
		
		// 답변 조회
		List<Answer> answerList = qnaService.answerList(questionNo);
		
		model.addAttribute("question", question);
		model.addAttribute("answerList", answerList);
		
		return "/admin/adminQnAOne";
	}
	
	// 문의 등록
	@PostMapping("/qna/addQuestion")
	public String addQuestion(HttpSession session,
							@ModelAttribute Question question) throws UnsupportedEncodingException {
		
		log.debug(question.toString() + "<-- QnAController addQuestion question");
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		log.debug(memberId+"<-- memberId");
		
		question.setMemberId(memberId);
		
		int addQuestionRow = qnaService.addQuestion(question);
		log.debug(addQuestionRow + "<-- QnAController addQuestion addQuestionRow");
		
		if(addQuestionRow == 0) {
			// 문의 등록 실패 시 msg
			msg = "문의 등록 실패"; 
		} else {
			// 문의 등록 성공 시 msg
			msg = "문의가 등록되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/qna/addQuestionForm?msg="+msg;
	}
	
	// 문의 수정
	@PostMapping("/qna/modifyQuestion")
	public String modifyQuestion(@ModelAttribute Question question) throws UnsupportedEncodingException {
		
		log.debug(question.toString() + "<-- QnAController modifyQuestion question");
		
		// questionNo 값 저장
		int questionNo = question.getQuestionNo();
		
		int modifyQuestionRow = qnaService.modifyQuestion(question);
		log.debug(modifyQuestionRow + "<-- QnAController modifyQuestion modifyQuestionRow");
		
		if(modifyQuestionRow == 0) {
			// 문의 수정 실패 시 msg
			msg = "문의 수정 실패"; 
		} else {
			// 문의 수정 성공 시 msg
			msg = "문의가 수정되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/qna/qnaOne?questionNo="+questionNo+"&msg="+msg;
	}
	
	// 문의 삭제
	@GetMapping("/qna/removeQuestion")
	public String removeQuestion(@RequestParam int questionNo) {
		
		int removeQuestionRow = qnaService.removeQuestion(questionNo);
		
		return "redirect:/qna/qnaList";
		
	}
	
	// 문의답변 등록
	@PostMapping("/qna/addAnswer")
	public String addAnswer(HttpSession session, 
							@ModelAttribute Answer answer) throws UnsupportedEncodingException {
		
		log.debug(answer.toString() + "<-- QnAController addAnswer answer");
		
		// questionNo 값 저장
		int questionNo = answer.getQuestionNo();
		
		// 세션에서 로그인 된 memberId 추출
		String memberId = (String) session.getAttribute("loginId");
		log.debug(memberId+"<-- memberId");
		
		answer.setMemberId(memberId);
		
		int addAnswerRow = qnaService.addAnswer(answer);
		log.debug(addAnswerRow + "<-- QnAController addAnswer addAnswerRow");
		
		if(addAnswerRow == 0) {
			// 문의답변 등록 실패 시 msg
			msg = "문의답변 등록 실패"; 
		} else {
			// 문의답변 등록 성공 시 msg
			msg = "문의 답변이 등록되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/admin/adminQnAOne?questionNo="+questionNo+"&msg="+msg;
	}
	
	// 문의답변 수정
	@PostMapping("/qna/modifyAnswer")
	public String modifyAnswer(@ModelAttribute Answer answer) throws UnsupportedEncodingException {
	
		log.debug(answer.toString() + "<-- QnAController modifyAnswer answer");
		
		// questionNo 값 저장
		int questionNo = answer.getQuestionNo();
		
		int modifyAnswerRow = qnaService.modifyAnswer(answer);
		log.debug(modifyAnswerRow + "<-- QnAController modifyAnswer modifyAnswerRow");
		
		if(modifyAnswerRow == 0) {
			// 문의답변 수정 실패 시 msg
			msg = "문의답변 수정 실패"; 
		} else {
			// 문의답변 수정 성공 시 msg
			msg = "답변이 수정되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/admin/adminQnAOne?questionNo="+questionNo+"&msg="+msg;
	}
	
	// 문의답변 삭제
	@GetMapping("/qna/removeAnswer")
	public String removeAnswer(@RequestParam int answerNo, 
								@RequestParam int questionNo) throws UnsupportedEncodingException {
		
		int removeAnswerRow = qnaService.removeAnswer(answerNo);
		
		if(removeAnswerRow == 0) {
			// 문의답변 삭제 실패 시 msg
			msg = "문의답변 삭제 실패"; 
		} else {
			// 문의답변 삭제 성공 시 msg
			msg = "답변이 삭제되었습니다"; 
		}
		
		// msg 인코딩
		msg = URLEncoder.encode(msg, "UTF-8");
		
		return "redirect:/admin/adminQnAOne?questionNo="+questionNo+"&msg="+msg;
	}
}
