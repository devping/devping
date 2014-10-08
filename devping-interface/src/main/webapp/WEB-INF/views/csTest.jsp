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
	<link href='http://fonts.googleapis.com/css?family=Fredericka+the+Great|Chelsea+Market|Enriqueta' rel='stylesheet' type='text/css'>
	<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet" />
	<!--[if lt IE 9]>
	<script src="/resources/lib/respond.min.js"></script>
	<![endif]-->
	<style>[touch-action="none"]{ -ms-touch-action: none; touch-action: none; }[touch-action="pan-x"]{ -ms-touch-action: pan-x; touch-action: pan-x; }[touch-action="pan-y"]{ -ms-touch-action: pan-y; touch-action: pan-y; }[touch-action="scroll"],[touch-action="pan-x pan-y"],[touch-action="pan-y pan-x"]{ -ms-touch-action: pan-x pan-y; touch-action: pan-x pan-y; }</style>
</head>
<body>
	<section class="container"
		data-ng-controller="PingPongController">
		<section>
			<div>
				<label for="tag-group">tag</label>
			</div>
			<div>
				<input class="input" type="text" placeholder="C, C++, java..."
					data-ng-model="pingTags" data-ng-change="traceTagName()">
				<button type="button" class="btn btn-primary"
						data-ng-click="searchUser()">선택</button>
			</div>
			<div>
				<label>Tag List</label>
			</div>
			<ul>
				<li data-ng-repeat="tag in tagList">
					{{ tag }}
				</li>
			</ul>
			<div>
				<label>User List - {{ totalMembers }} 명</label>
			</div>
			<ul>
				<li data-ng-repeat="userIdsWithTag in userIdsWithTag">
					<div class="input-group">
						<span class="input-group-addon">
							<input type="checkbox">
						</span>
						<div>
							{{ userIdsWithTag.userId }} - {{ userIdsWithTag.nickName }}
						</div>
					</div>
				</li>
			</ul>
			<div>
				<label>question</label>
			</div>
			<textarea rows="3"
				data-ng-model="pingQuestion"></textarea>
			<button type="button" class="btn btn-primary"
					data-ng-click="ping()">Ping</button>
			<div>
				<label>channelID</label>
			</div>
			<div>
				{{ roomId }}
			</div>
		</section>
	</section>

	<!-- Vendor Libs: jQuery only used for Bootstrap functionality -->
	<script src="<c:url value="/resources/lib/angular.min.js"/>"></script>
	<script src="<c:url value="/resources/lib/angular-route.min.js"/>"></script>
	<script src="<c:url value="/resources/lib/jquery.min.js"/>"></script>
	<!-- UI Libs -->
	<script src="<c:url value="/resources/lib/bootstrap.min.js"/>"></script>
	<!-- App libs -->
	<script src="<c:url value="/resources/js/app.js"/>"></script>
	<script src="<c:url value="/resources/js/controllers.js"/>"></script>
	<script src="<c:url value="/resources/js/services.js"/>"></script>
</body>
</html>
