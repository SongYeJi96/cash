<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div class="container-scroller">
   <!-- header -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <!-- 로고 -->
        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
            <a class="navbar-brand brand-logo mr-5" href="/cash/home">
                <img src="/cash/resource/images/logo.png" class="mr-2" alt="logo"/>
            </a>
        </div>
        <!-- 세션 값에 따른 로그인, 로그아웃, 회원가입 버튼 분기 -->
        <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
            <c:choose>
            	<c:when test="${sessionScope.loginIdLevel == null}">
            		<a href="/cash/login/loginForm" class="mr-2 aTag">로그인</a>
            		<span>&#124;</span>
					<a href="/cash/signUp" class="ml-2 aTag">회원가입</a>
            	</c:when>
            	<c:otherwise>
					<a href="/cash/login/logout" class="aTag">
					   <i class="ti-power-off text-primary"></i> 로그아웃
					</a>
            	</c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>
</body>
</html>