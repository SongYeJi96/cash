<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="/WEB-INF/view/inc/head.jsp"/>
<body>
	<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth px-0">
            <div class="row w-100 mx-0">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                        <div class="brand-logo">
                            <a href="/cash/home">
                            	<img src="/cash/resource/images/logo.png" alt="logo">
                            </a>
                        </div>
                        <h4>로그인</h4>
                        <div class="pt-3">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="memberId" value="admin"
                                    placeholder="아이디"
                                    
                                    <c:if test="${not empty loginId}">
									value="${loginId}"
									</c:if>
                                 >
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="memberPw" value="1234"
                                    placeholder="비밀번호">
                            </div>
                            <div class="mt-3">
	                            <button type="button" id="loginBtn"
	                            class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">로그인</button>
                            </div>
                            <div class="my-2 d-flex justify-content-between align-items-center">
                                <div class="form-check">
                                    <label class="form-check-label text-muted">
                                        <input type="checkbox" class="form-check-input" id="idSave"> 아이디 저장
                                    </label>
                                </div>
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                <span>계정이 없으신가요?</span> <a href="/cash/signUp" class="text-primary">회원가입</a>
                            </div>
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
<script src="/cash/resource/js/customJs/loginForm.js"></script>
</html>