<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<h4 class="font-weight-bold">월 별 수입/지출 통계</h4>
					<hr>
					<!-- 년도별 조회 -->
					<select id="yearSelect" class="form-control form-control-sm col-sm-1">
				    	<!-- 셀렉트 옵션 동적으로 생성 -->
					</select>
					
					<!-- 월 별 수입/지출 차트 -->
					<div>
						<canvas id="incomeAndExpenseChart" height="500"></canvas>
					</div>
					
					</div>
				</div>
			</div>		
	 		
	 	</div>
	 </div>	

</div>
</body>
<!-- script -->
<jsp:include page="/WEB-INF/view/inc/script.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<script src="/cash/resource/js/customJs/statistics.js"></script>
</html>