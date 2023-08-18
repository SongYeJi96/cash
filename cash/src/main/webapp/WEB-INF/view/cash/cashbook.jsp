<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>${targetYear}년 ${targetMonth+1}월</h1>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}/cash/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
		<a href="${pageContext.request.contextPath}/cash/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
	</div>
	
	<div>
		<table border="1">
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
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
						<td>
							<div>${day}</div>
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
		</table>
	</div>
</body>
</html>