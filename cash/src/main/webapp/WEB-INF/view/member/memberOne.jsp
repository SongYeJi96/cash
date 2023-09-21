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
						<!-- 회원정보 상세 -->
						<div id="memberOne">
							<h4 class="font-weight-bold">회원정보</h4>
							<hr>
							<div>
								<table class="table table-borderless">
									<tr>
										<th width="10%">아이디</th>
										<td>${member.memberId}</td>
									</tr>
									<tr>
										<th>이름</th>
										<td>${member.memberName}</td>
									</tr>
									<tr>
										<th>이메일</th>
										<td>${member.memberEmail}</td>
									</tr>
									<tr>
										<th>가입일</th>
										<td>${member.createdate}</td>
									</tr>
									<tr>
										<th>정보수정일</th>
										<td>${member.updatedate}</td>
									</tr>
								</table>
								<br>
								<div class="text-center">
									<button type="button" id="modifyMemberFormBtn" class="btn btn-md btn-primary">회원정보 수정</button>
									<button type="button" id="modifyPwFormBtn" class="btn btn-md btn-primary">비밀번호 변경</button>
									<a href="/cash/member/cancelMember" class="btn btn-md btn-primary">회원탈퇴</a>
								</div>
							</div>
						</div>
						<!-- 회원정보 수정 -->
						<div id="modifyMember" style="display: none">
							<h4 class="font-weight-bold">회원정보 수정</h4>
							<hr>
							<form id="modifyMemberForm" method="post">
								<table class="table table-borderless">
									<tr>
										<th width="10%">아이디</th>
										<td>
											${member.memberId}
											<input type="hidden" name="memberId" value="${member.memberId}">
										</td>
									</tr>
									<tr>
										<th>이름</th>
										<td>
											<input type="text" id="memberName" name="memberName" value="${member.memberName}" 
													class="form-control w-25">
										</td>
									</tr>
									<tr>
										<th>이메일</th>
										<td>
											<input type="email" id="memberEmail" name="memberEmail" value="${member.memberEmail}"
													class="form-control w-25"> 
											<span id="emailValidationMessage"></span>
										</td>
									</tr>
									<tr>
										<th>가입일</th>
										<td>${member.createdate}</td>
									</tr>
									<tr>
										<th>정보수정일</th>
										<td>${member.updatedate}</td>
									</tr>
								</table>
								<br>
								<div class="text-center">
									<button type="button" id="modifyMemberBtn" class="btn btn-md btn-primary">수정</button>
									<button type="button" class="cancelBtn btn btn-md btn-primary">취소</button>
								</div>
							</form>
						</div>
						<!-- 비밀번호 변경 -->
						<div id="modifyPw" style="display: none">
							<form id="modifyPwForm" method="post">
								<h4 class="font-weight-bold">비밀번호 변경</h4>
								<hr>
								<input type="hidden" name="memberId" value="${member.memberId}">
								<table class="table table-borderless">
									<tr>
										<th width="10%">비밀번호</th>
										<td>
											<input type="password" id="memberPw" name="memberPw" class="form-control w-25">
										</td>
									</tr>
									<tr>
										<th>비밀번호 확인</th>
										<td>
											<input type="password" id="confirmPw" class="form-control w-25">
											<span id="pwMatchMessage"></span>
										</td>
									</tr>
								</table>
								<br>
								<div class="text-center">
									<button type="button" id="modifyPwBtn" class="btn btn-md btn-primary">변경</button>
									<button type="button" class="cancelBtn btn btn-md btn-primary">취소</button>
								</div>
							</form>
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
<script src="/cash/resource/js/customJs/memberOne.js"></script>
</html>