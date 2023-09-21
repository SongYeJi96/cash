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
						<h4 class="font-weight-bold">회원탈퇴</h4>
						<hr>
						<form id="cancelMemberForm" method="post">
							<input type="hidden" id="memberId" name="memberId" value="${sessionScope.loginId}">
							
							<table class="table table-borderless">
								<tr>
									<td>회원탈퇴를 위해 비밀번호를 입력해주세요</td>
								</tr>
								<tr>
									<th width="10%">비밀번호</th>
									<td>
										<input type="password" id="memberPw" class="form-control w-25">
									</td>
								</tr>
							</table>
							<div class="text-center">
								<button type="button" id="cancelMemberBtn" class="btn btn-md btn-primary">회원탈퇴</button>
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
<script src="/cash/resource/js/customJs/cancelMember.js"></script>
</html>