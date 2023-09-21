<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <h4>회원가입</h4>
                        <form id="addMemberForm" method="post" class="pt-3">
                            <div class="form-group d-flex align-items-center">
                                <input type="text" class="form-control form-control-lg" id="memberId" name="memberId"
                                    style="width: 300px;" placeholder="아이디">
                                <button type="button" id="idCheckBtn"
                                		class="btn btn-primary font-weight-medium ml-3">중복검사</button>    
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="memberPw" name="memberPw"
                                    placeholder="비밀번호">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control form-control-lg" id="confirmPw"
                                    placeholder="비밀번호 확인">
                                <span id="pwMatchMessage"></span>    
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="memberName" name="memberName"
                                    placeholder="이름">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-lg" id="memberEmail" name="memberEmail"
                                    placeholder="이메일">
                                <span id="emailValidationMessage"></span>    
                            </div>
                            <div class="mt-3">
                                <button type="button" id="addMemberBtn"
                                		class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">회원가입</button>   
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                계정이 있으신가요? <a href="/cash/login/loginForm" class="text-primary">로그인</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- script -->
<jsp:include page="/WEB-INF/view/inc/script.jsp"/>	
<script src="/cash/resource/js/customJs/signUp.js"></script>
</html>