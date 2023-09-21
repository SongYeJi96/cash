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
							<br>
							<!-- 수정, 삭제 버튼. 작성자에 따른 버튼 활성화 분기 -->
							<c:if test="${sessionScope.loginId == question.memberId}">
								<div class="text-center">
									<button type="button" id="modifyQuestonFormBtn" class="btn btn-md btn-primary">수정</button>
									<button type="button" id="removeQuestonBtn" class="btn btn-md btn-primary">삭제</button>
								</div>
							</c:if>
						</div>
					</div>
					<!-- 문의 수정폼 -->
					<div id="modifyQuestion" style="display: none">
						<h4 class="font-weight-bold">문의 수정</h4>
						<hr>
						<form id="modifyQuestionForm" method="post">
							<input type="hidden" id="questionNo" name="questionNo" value="${question.questionNo}">
							<table class="table table-borderless">
								<tr>
									<td>
										<input type="text" id="questionTitle" name="questionTitle" 
											value="${question.questionTitle}" placeholder="제목을 입력해주세요" class="form-control">
									</td>
								</tr>
								<tr>
									<td>
										<textarea rows="20" id="questionContent" 
											name="questionContent" placeholder="내용을 입력해주세요" class="form-control">${question.questionContent}</textarea>
									</td>
								</tr>
							</table>
							<div class="text-center">
								<button type="button" id="modifyQuestionBtn" class="btn btn-md btn-primary">수정</button>
								<button type="button" id="cancelBtn" class="btn btn-md btn-primary">취소</button>
							</div>
						</form>
					</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<!-- 문의 답변 출력 -->
						<h4 class="font-weight-bold">답변</h4>
						<hr>
						<c:forEach var="a" items="${answerList}">
							<p>${a.answerContent}</p><br>
						</c:forEach>
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
<script src="/cash/resource/js/customJs/qnaOne.js"></script>
</html>