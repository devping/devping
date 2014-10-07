<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DevPing</title>
<style>
</style>
</head>
<body>
<input type=text size=20 placeholder="ID"><br />
<input type=text size=20 placeholder="Password"><br />
<input type=submit value="회원 로그인">

<!-- 구글 로그인 사용자를 위한 google sign in 링크 -->
<a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&state=%2Fprofile&redirect_uri=https%3A%2F%2Ftest.devping.com%3A8443%2Foauth2callback&response_type=code&client_id=298688116141-tomf8f4jkunlankbd4c55nu9bp83h963.apps.googleusercontent.com&approval_prompt=force">구글로 로그인</a>

</body>
</html>
