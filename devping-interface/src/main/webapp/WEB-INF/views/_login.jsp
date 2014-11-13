<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html lang="ko" data-ng-app="devPingApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>DevPing</title>
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen"/>
	<link href="<c:url value="/resources/css/styles_login.css"/>" rel="stylesheet" />
	<!--[if lt IE 9]>
	<script src="/resources/lib/respond.min.js"></script>
	<![endif]-->
	<style>[touch-action="none"]{ -ms-touch-action: none; touch-action: none; }[touch-action="pan-x"]{ -ms-touch-action: pan-x; touch-action: pan-x; }[touch-action="pan-y"]{ -ms-touch-action: pan-y; touch-action: pan-y; }[touch-action="scroll"],[touch-action="pan-x pan-y"],[touch-action="pan-y pan-x"]{ -ms-touch-action: pan-x pan-y; touch-action: pan-x pan-y; }</style>
</head>
<body>
	<section class="container">
			<h2 class="jb-c-form-signin-heading">DevPing</h2>
        <div id="social" class="jb-c-form-signin">
			<div class="jb-c-login-wrap jb-c-login-social-link">
				<span>소셜 계정으로 로그인하기</span>
                <form id="facebook" method="post" action="/auth/facebook">
                    <a href="index.html" class="jb-c-facebook">
                        Facebook
                    </a>
                </form>
                <form id="google" method="post" action="/auth/google">
                    <a href="index.html" class="jb-c-twitter">
                        Google
                    </a>
                </form>
			</div>
            <span>or</span>
            <form id="devping" method="post" action="${pageContext.request.contextPath}/signin/authenticate">
                <div class="jb-c-login-wrap">
                    <span>DevPing 계정으로 로그인하기</span>
                    <input type="hidden" id="type" name="type" value="login">
                    <input type="text" class="form-control" id="user-email" name="username" placeholder="아이디" autofocus="autofocus">
                    <input type="password" class="form-control" id="user-password" name="password" placeholder="비밀번호">
                    <button class="btn btn-lg btn-login btn-block" type="submit">로그인</button>
                </div>
            </form>

            <div class="jb-c-registration">
                <a data-toggle="modal" data-target="#registrationModal">회원가입</a>
				<span class="pull-right">
					<a>아이디찾기</a>
					<a>비밀번호찾기</a>
				</span>
            </div>
        </div>
	</section>
	
	<!-- Modal -->
	<div class="modal fade" id="registrationModal" tabindex="-1" role="dialog"
		aria-labelledby="Ping" aria-hidden="true" data-backdrop="static">
		<form class="modal-dialog" method="post" action="/main">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="pingModalLabel">회원가입</h4>
				</div>
				<div class="modal-body">
					<div>
						<input type="hidden" id="type" name="type" value="regist">
						<input type="text" class="form-control" id="id" name="id" placeholder="아이디" autofocus="autofocus">
						<input type="email" class="form-control" id="email" name="email" placeholder="이메일">
						<input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호">
						<input type="password" class="form-control" id="pw-re" name="pw-re" placeholder="비밀번호 확인">
						<input type="text" class="form-control" id="tags" name="tags" placeholder="관심 Tags">
						<span>여러 tag를 입력하실 때는 ',' 로 구분해서 입력하시면 됩니다.</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary">회원가입</button>
				</div>
			</div>
		</form>
	</div>

	<!-- Vendor Libs: jQuery only used for Bootstrap functionality -->
	<script src="<c:url value="/resources/lib/jquery.min.js"/>"></script>
	<!-- UI Libs -->
	<script src="<c:url value="/resources/lib/bootstrap.min.js"/>"></script>
</body>
</html>
