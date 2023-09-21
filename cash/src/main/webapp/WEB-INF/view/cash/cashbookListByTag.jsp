<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h3 class="font-weight-bold">해시태그 &#91;&#35;<span id="word">${word}</span>&#93;</h3>
						<hr>
						<!-- 해시태그 리스트 일별 검색폼 -->
						<div class="row justify-content-end">
							<input type="date" id="startDate" class="form-control form-control-sm" style="width: 200px;">
							<span class="ml-2 mr-2 d-flex align-items-center">&#126;</span>
							<input type="date" id="endDate" class="form-control form-control-sm mr-3" style="width: 200px;">
							<button type="button" id="searchHashTagListBtn" class="btn btn-sm btn-primary mr-3">검색</button>
						</div>
						<br>
						<!-- 해시태그 리스트 출력 -->
						<div>
							<table class="table">
								<thead>
									<tr>
										<th width="10%" class="text-center">번호</th>
										<th class="text-center">날짜</th>
										<th class="text-center">수입/지출</th>
										<th class="text-center" >금액</th>
										<th width="40%">내역</th>
									</tr>
								</thead>
								<tbody id="hashTagList">
								
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
<script src="/cash/resource/js/customJs/cashbookListByTag.js"></script>
</html>