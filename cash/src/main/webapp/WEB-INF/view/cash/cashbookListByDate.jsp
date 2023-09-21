<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="/WEB-INF/view/inc/head.jsp"/>
<body>
<!-- header -->
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
						<h4 class="font-weight-bold">가계부 작성</h4>
						<hr>
						<form id="addCashbookForm" method="post">
							<table class="table table-borderless">
								<tr>
									<th width="10%">날짜</th>
									<td>
										<input type="date" name="cashbookDate" value="${cashbookDate}" class="form-control w-25">
									</td>
								</tr>
								<tr>
									<th>수입/지출</th>
									<td>
										<input type="radio" id="category" name="category" value="수입"> 수입
										<input type="radio" name="category" value="지출"> 지출
									</td>
								</tr>
								<tr>
									<th>금액</th>
									<td>
										<input type="number" id="price" name="price" class="form-control w-25">
									</td>
								</tr>
								<tr>
									<th>내역</th>
									<td>
										<textarea rows="3" id="memo" name="memo" class="form-control"></textarea>
									</td>
								</tr>
							</table>
							<br>
							<div class="text-center">
								<button type="button" id="addCashbookBtn" class="btn btn-md btn-primary">등록</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="font-weight-bold">${targetYear}년 ${targetMonth}월 ${day}일 입출금 목록</h4>
						<hr>
						<table class="table">
							<thead>
								<tr>
									<th>번호</th>
									<th>수입/지출</th>
									<th>날짜</th>
									<th>금액</th>
									<th>내역</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="c" items="${cListByDate}">
									<tr onclick="location.href='/cash/cashbook/cashbookOne?cashbookNo=${c.cashbookNo}'"
										class="selectTr">
										<td>${c.cashbookNo}</td>
										<td>${c.category}</td>
										<td>${c.cashbookDate}</td>
										<td>${c.price}</td>
										<td>${c.memo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
<script src="/cash/resource/js/customJs/cashbookListByDate.js"></script>
</html>