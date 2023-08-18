<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>login</h3>
	<form id="loginForm">
		<div>
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="memberId" name="memberId"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="memberPw" name="memberIPw"></td>
				</tr>
			</table>
		</div>
		<div>
			<button type="buttoon" id="loginBtn">로그인</button>
		</div>
		<div>
			<input type="checkbox" id="idSave" name="idSave" value="y">
			<span>아이디 저장</span>
		</div>
	</form>
</body>
</html>