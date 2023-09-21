<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="/cash/home">
                <i class="mdi mdi-calendar-plus menu-icon" style="font-size: 20px;"></i>
                <span class="menu-title">가계부</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/cash/cashbook/statistics">
                <i class="icon-bar-graph menu-icon" style="font-size: 20px;"></i>
                <span class="menu-title">통계</span>  
            </a>  
        </li>
        <!-- loginIdLevel에 따른 문의 메뉴 활성화 분기(회원 level==0) -->
        <c:if test="${sessionScope.loginIdLevel == 0 || sessionScope.loginIdLevel == null}">
	        <li class="nav-item">
	            <a class="nav-link" href="/cash/qna/qnaList">
	                <i class="mdi mdi-comment-question-outline menu-icon" style="font-size: 20px;"></i>
	                <span class="menu-title">문의</span> 
	            </a>
	        </li>
        </c:if>
        <!-- loginIdLevel에 따른 문의관리 메뉴 활성화 분기(관리자 level==1) -->
        <c:if test="${sessionScope.loginIdLevel == 1}">
	        <li class="nav-item">
	            <a class="nav-link" href="/cash/admin/adminQnAList">
	                <i class="mdi mdi-comment-question-outline menu-icon" style="font-size: 20px;"></i>
	                <span class="menu-title">문의관리</span> 
	            </a>
	        </li>
        </c:if>
        <li class="nav-item">
            <a class="nav-link" href="/cash/member/memberOne?memberId=${sessionScope.loginId}">
                <i class="mdi mdi-account-outline menu-icon" style="font-size: 20px;"></i>
                <span class="menu-title">나의정보</span>
            </a>
        </li>
    </ul>
</nav>
</body>
</html>