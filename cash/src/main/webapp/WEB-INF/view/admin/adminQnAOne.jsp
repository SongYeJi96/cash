<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="/WEB-INF/view/inc/head.jsp"/>
<body>
<!-- head -->
<jsp:include page="/WEB-INF/view/inc/header.jsp"/>
<div class="container-fluid page-body-wrapper">
<!-- sideContent -->
<jsp:include page="/WEB-INF/view/inc/sideContent.jsp"/>	
	
	<!-- main -->
	 <div class="main-panel">
	 	<div class="content-wrapper">
	 		<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<!-- 문의 상세 출력 -->
						<div id="qnaOne">
							<div>
								<h3 class="font-weight-bold">${question.questionTitle}</h3>
							</div>
							<div>
								<p>작성자&nbsp;&#58;&nbsp;${question.questionWriter}&nbsp;&#40;${question.createdate}&#41;</p>
							</div>
							<hr>	
							<div>
								<p>${question.questionContent}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<!-- 문의 답변폼 -->
						<div>
							<form id="addAnswerForm" method="post">
								<input type="hidden" id="questionNo" name="questionNo" value="${question.questionNo}">
								<div class="d-flex justify-content-between">
									<h4 class="font-weight-bold">답변</h4>
									<button type="button" id="addAnswerBtn" class="btn btn-sm btn-primary">등록</button>
								</div>
								<hr>
								<textarea rows="5" id="answerContent" name="answerContent" class="form-control"></textarea>
							</form>
						</div>
						<br>
						<hr>
						<!-- 문의답변 출력 -->
						<div>
							<c:if test="${not empty answerList}">
								<table class="table table-borderless">
									<c:forEach var="a" items="${answerList}">
										<tr id="answerList${a.answerNo}">
											<td width="90%">${a.answerContent}</td>
											<td>
												<button type="button" class="modifyAnswerFormBtn btn btn-sm btn-primary" 
														data-answer-no="${a.answerNo}">수정</button>
												<button type="button" class="removeAnswerBtn btn btn-sm btn-primary" 
														data-answer-no="${a.answerNo}">삭제</button>
											</td>
										</tr>
										<!-- 문의답변 수정 -->
										<tr id="modifyAnswer${a.answerNo}" style="display:none;">
											<td width="90%">
												<form id="modifyAnswerForm${a.answerNo}" method="post">
													<input type="hidden" name="answerNo" value="${a.answerNo}">
													<input type="hidden" name="questionNo" value="${a.questionNo}">
													<textarea rows="5" class="answerContent form-control" name="answerContent">${a.answerContent}</textarea>
												</form>
											</td>
											<td>
												<button type="button" class="modifyAnswerBtn btn btn-sm btn-primary" 
														data-answer-no="${a.answerNo}">수정</button>
												<button type="button" class="cancelBtn btn btn-sm btn-primary" 
														data-answer-no="${a.answerNo}">취소</button>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
					</div>
				</div>
			</div>				
	 	</div>
	 	
	 	<!-- footer -->
	 	<jsp:include page="/WEB-INF/view/inc/footer.jsp"/>
	 	
	 </div>	
</div>
</body>
<!-- script -->
<jsp:include page="/WEB-INF/view/inc/script.jsp"/>
<script src="/cash/resource/js/customJs/adminQnAOne.js"></script>
</html>