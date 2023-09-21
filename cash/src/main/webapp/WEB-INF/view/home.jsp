<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	 		<div class="row">
		 		<div class="col-md-12 grid-margin">
		 			
		 			<!-- 월별 해시태그 -->
					<div class="col-md-12 mb-4 stretch-card transparent">
						<div class="card card-light-danger">
							<div class="card-body">
								<h4 class="mb-4 font-weight-bold">이달의 해시태그</h4>
								<c:forEach var="h" items="${hashTagList}">
									<a class="hashTag mr-2" href="/cash/cashbook/cashbookListByTag?word=${h.word}">${h.word}(${h.cnt})</a>
								</c:forEach>
							</div>
						</div>
					</div>
		 			
		 			<!-- 월별 수입, 지출, 잔액 합계 -->
		 			<div class="col-md-12 grid-margin transparent">
						<div class="row">
							<div class="col-md-4 mb-4 stretch-card transparent">
								<div class="card card-tale">
									<div class="card-body">
										<h4 class="mb-4">${targetYear}년 ${targetMonth+1}월 수입</h4>
										<p class="fs-30 mb-2">${totalIncome}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4 mb-4 stretch-card transparent">
								<div class="card card-light-blue">
									<div class="card-body">
										<h4 class="mb-4">${targetYear}년 ${targetMonth+1}월 지출</h4>
										<p class="fs-30 mb-2">${totalExpense}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4 mb-4 stretch-card transparent">
								<div class="card card-dark-blue">
									<div class="card-body">
										<h4 class="mb-4">${targetYear}년 ${targetMonth+1}월 잔액</h4>
										<p class="fs-30 mb-2">${balance}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					
		 			<!-- 월별 가계부 -->
		 			<div class="col-md-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<!-- 년, 월 표시 -->
									<div class="col">
										<h3 class="font-weight-bold">
											${targetYear}년 ${targetMonth+1}월
										</h3>
									</div>
									<!-- 연월 이동 버튼. 입사 연월에 따른 이전달, 다음달 버튼 분기-->
									<div class="col d-flex justify-content-end align-items-center">
										<div class="btn-group" role="group" >
											<a href="/cash/home?targetYear=${targetYear}&targetMonth=${targetMonth-1}"
												class="btn btn-dark btn-sm">이전달</a>
											<a href="/cash/home?targetYear=${targetYear}&targetMonth=${targetMonth+1}"
												class="btn btn-dark btn-sm">다음달</a>
										</div>
									</div>
								</div>
								
								<div>
									<table class="table fixed-table">
										<thead>
											<tr>
												<th class="cashbookth">일</th>
												<th class="cashbookth">월</th>
												<th class="cashbookth">화</th>
												<th class="cashbookth">수</th>
												<th class="cashbookth">목</th>
												<th class="cashbookth">금</th>
												<th class="cashbookth">토</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:forEach var="i" begin="0" end="${totalTd - 1}" step="1">
													<c:set var="day" value="${i-beginBlank+1}"></c:set>
													
													<c:if test="${i!=0 && i%7==0}">
														</tr><tr>
													</c:if>
													
													<c:if test="${day < 1 || day > lastDate}">
														<td></td>
													</c:if>
													
													<c:if test="${!(day < 1 || day > lastDate)}">
														<td class="cashbooktd" onclick="location.href='/cash/cashbook/cashbookListByDate?targetYear=${targetYear}&targetMonth=${targetMonth+1}&day=${day}'">
															<span class="cashbookDay">${day}</span>
															<c:forEach var="c" items="${cashbookList}">
																<c:if test="${day == fn:substring(c.cashbookDate,8,10)}">
																	<div>
																		<c:if test="${c.category == '수입'}">
																			<span>+${c.price}</span>
																		</c:if>
																		<c:if test="${c.category == '지출'}">
																			<span style="color:red;">-${c.price}</span>
																		</c:if>
																	</div>
																</c:if>
															</c:forEach>
														</td>
													</c:if>
												</c:forEach>
											</tr>
										</tbody>
									</table>
								</div>
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
</html>