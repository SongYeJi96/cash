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
						<!-- 가계부 상세 -->
						<div id="cashbookOne">
							<h4 class="font-weight-bold">가계부 상세 내역</h4>
							<hr>
							<table class="table table-borderless">
								<tr>
									<th width="10%">번호</th>
									<td>
										<span id="cashbookNo">${cashbookOne.cashbookNo}</span>
									</td>	
								</tr>
								<tr>
									<th>날짜</th>
									<td>
										<span id="cashbookDate">${cashbookOne.cashbookDate}</span>
									</td>
								</tr>
								<tr>
									<th>수입/지출</th>
									<td>
										<span id="cashCategory">${cashbookOne.category}</span>
									</td>
								</tr>
								<tr>
									<th>금액</th>
									<td>
										<span>${cashbookOne.price}</span>
									</td>
								</tr>
								<tr>
									<th>내역</th>
									<td>
										<p>${cashbookOne.memo}</p>
									</td>
								</tr>
							</table>
							<div class="text-center">
								<button type="button" id="modifyBtn" class="btn btn-md btn-primary">수정</button>
								<button type="button" id="removeBtn" class="btn btn-md btn-primary">삭제</button>
								<button type="button" class="cashbookListBtn btn btn-md btn-primary">목록</button>
							</div>
						</div>
						
						<!-- 가계부 수정폼 -->
						<div id="modifyCashbook" style="display:none">
							<form id="modifyCashbookForm" method="post">
								<h4 class="font-weight-bold">가계부 수정</h4>
								<hr>
								<table class="table table-borderless">
									<tr>
										<th width="10%">번호</th>
										<td>
											<span>${cashbookOne.cashbookNo}</span>
											<input type="hidden" name="cashbookNo" value="${cashbookOne.cashbookNo}">
										</td>	
									</tr>
									<tr>
										<th>날짜</th>
										<td>
											<span>${cashbookOne.cashbookDate}</span>
										</td>
									</tr>
									<tr>
										<th>수입/지출</th>
										<td id="categoryInput">
											<input type="radio" id="category" name="category" value="수입"> 수입
											<input type="radio" id="category" name="category" value="지출"> 지출
										</td>
									</tr>
									<tr>
										<th>금액</th>
										<td>
											<input type="number" id="price" name="price" value="${cashbookOne.price}" class="form-control w-25">
										</td>
									</tr>
									<tr>
										<th>내역</th>
										<td>
											<textarea rows="3" id="memo" name="memo" class="form-control">${cashbookOne.memo}</textarea>
										</td>
									</tr>
								</table>
							</form>
							<div class="text-center">
								<button type="button" id="confirmBtn" class="btn btn-md btn-primary">확인</button>
								<button type="button" id="cancelBtn" class="btn btn-md btn-primary">취소</button>
								<button type="button" class="cashbookListBtn btn btn-md btn-primary">목록</button>
							</div>
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
<script src="/cash/resource/js/customJs/cashbookOne.js"></script>
</html>