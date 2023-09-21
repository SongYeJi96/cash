<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<h4 class="font-weight-bold">문의 작성</h4>
						<hr>
						<form id="addQuestionForm" method="post">
							<table class="table table-borderless">
								<tr>
									<td>
										<input type="text" id="questionTitle" name="questionTitle" placeholder="제목을 입력해주세요"
												class="form-control">
									</td>
								</tr>
								<tr>
									<td>
										<textarea rows="20" id="questionContent" name="questionContent" placeholder="내용을 입력해주세요"
												class="form-control"></textarea>
									</td>
								</tr>
							</table>
							<br>
							<div class="text-center">
								<button type="button" id="addQuestionBtn" class="btn btn-md btn-primary">등록</button>
							</div>
						</form>
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
<script src="/cash/resource/js/customJs/addQuestion.js"></script>
</html>