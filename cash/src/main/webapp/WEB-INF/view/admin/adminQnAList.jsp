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
						<h4 class="font-weight-bold">문의 목록</h4>
						<hr>
						<!-- 문의 리스트 검색폼 -->
						<div class="form-inline justify-content-end">
							<div class="form-group">
								<select id="searchQuestion" class="form-control form-control-sm">
									<option value="question_title">제목</option>
						            <option value="question_content">내용</option>
						            <option value="question_title_content">제목+내용</option>
						            <option value="question_writer">작성자</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" id="searchText" class="form-control form-control-sm mr-2">
							</div>
							<div class="form-group">
								<button type="button" id="searchQnAListBtn" class="btn btn-md btn-primary">검색</button>
							</div>
						</div>
						<br>
						<!-- 문의 리스트 출력 -->
						<div>
							<table class="table">
								<thead>
									<tr>
										<th width="10%" class="text-center">번호</th>
										<th width="50%">제목</th>
										<th class="text-center">작성자</th>
										<th class="text-center">작성일</th>
										<th width="10%" class="text-center">답변상태</th>
									</tr>
								</thead>
								<tbody id="qnaList">
								
								</tbody>
							</table>
						</div>
						
						<!-- 페이지 네비게이션 -->
						<div id="pagination">
										
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
<script src="/cash/resource/js/customJs/adminQnAList.js"></script>
</html>