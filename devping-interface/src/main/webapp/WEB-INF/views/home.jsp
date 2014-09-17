<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html lang="ko" data-ng-app="devPingApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>DevPing</title>
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Fredericka+the+Great|Chelsea+Market|Enriqueta' rel='stylesheet' type='text/css'>
	<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet" />
	<!--[if lt IE 9]>
	<script src="/resources/lib/respond.min.js"></script>
	<![endif]-->
	<style>[touch-action="none"]{ -ms-touch-action: none; touch-action: none; }[touch-action="pan-x"]{ -ms-touch-action: pan-x; touch-action: pan-x; }[touch-action="pan-y"]{ -ms-touch-action: pan-y; touch-action: pan-y; }[touch-action="scroll"],[touch-action="pan-x pan-y"],[touch-action="pan-y pan-x"]{ -ms-touch-action: pan-x pan-y; touch-action: pan-x pan-y; }</style>
</head>
<body>
	<header class="navbar navbar-default navbar-fixed-top" data-ng-controller="SummaryController">
		<div class="container">
			<div class="navbar-inner jb-title-font">
				DevPing
				<div class="pull-right jb-summary-unit">
					<label>{{ summary.name }}</label><br/>
					<label>Point : {{ summary.point }}</label>
				</div>
			</div>
		</div>
	</header>

	<div data-ng-view=""></div>

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
