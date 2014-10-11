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
	<section id="container"
		data-ng-controller="PingPongController">
		<header class="header">
<!--Not yet 'side menu toogle'-->
			<div class="sidebar-toggle-box">
				<div data-original-title="Toggle Navigation" data-placement="right" class="fa fa-bars tooltips"></div>
			</div>
			<a href="main" class="logo">DevPing</a>
		</header>
		<aside>
			<div id="jb-c-navbar-colleapse" class="nav-collapse" tabindex="5000" style="overflow: hidden; outline: none;">
				<ul>
<!--Not yet 'My Info'-->
					<li class="jb-c-sub-menu">
						<div class="jb-c-info jb-c-info-square">
							<i class="jb-c-info-square-in"></i>
							<span>My Info</span>
						</div>
					</li>
					<li class="jb-c-sub-menu">
						<button class="btn btn-primary jb-c-info jb-c-info-circle"
							data-toggle="modal" data-target="#pingModal">
							<span>PING</span>
						</button>
					</li>
					<li class="jb-c-sub-menu">
						<div class="jb-c-badge-border">
							<span class="badge jb-c-bg-success">{{ pongCount }}</span>
						</div>
						<button class="btn btn-primary jb-c-info jb-c-info-circle"
							data-toggle="modal" data-target="#pongModal">
							<span>PONG</span>
						</button>
					</li>
				</ul>
			</div>
		</aside>
		<section id="jb-c-main-content">
			<div id="jb-c-content-view">
				<aside id="jb-c-chatting-grouptac-view">
					<div id="jb-c-chatting-grouptac-head">
						<h3>Room</h3>
					</div>
					<ul class="jb-c-chat-list">
						<li data-ng-repeat="room in myRoomList | orderBy:'time':reverse=true"
							data-ng-class="{'active': room.channelId == channelId}">
							<div class="jb-c-chat-item"
								data-ng-click="changeRoom(room.channelId)">
								<span>{{ room.channelId }} {{ room.tagList }}</span>
								<span class="badge jb-c-bg-important pull-right">{{ room.pingCountdown }}</span>
							</div>
						</li>
					</ul>
				</aside>
				<aside id="jb-c-chatting-view">
					<div id="jb-c-chatting-view-head">
						<h3>Chat {{ channelId }}</h3>
					</div>
					<div class="jb-c-chatting"
						data-ng-repeat="chat in chatList[channelId] | orderBy:'time'">
						<div class="first-part odd">{{ chat.user }}</div>
						<div class="second-part">{{ chat.content }}</div>
						<div class="third-part">{{ chat.time }}</div>
					</div>
					<footer data-ng-show="chatInputChecked">
						<div class="jb-c-chatting-box">
							<input type="text" class="form-control"
								data-ng-model="myMessage">
						</div>
						<button class="btn btn-danger full-right"
							data-ng-click="sendMessage()">Send</button>
					</footer>
				</aside>
				<aside id="jb-c-user-view">
					<div id="jb-c-user-view-head"></div>
					<ul class="jb-c-chat-available-user">
						<li data-ng-repeat="user in userList[channelId] | orderBy:'id'">
							{{ user.id }}
						</li>
					</ul>
				</aside>
			</div>
		</section>
		<!-- Modal -->
		<div class="modal fade" id="pingModal" tabindex="-1" data-role="dialog"
			data-aria-labelledby="Ping" data-aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span data-aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="pingModalLabel">Ping</h4>
					</div>
					<div class="modal-body">
						<div>
							<label for="tag-group">tag</label>
						</div>
						<div class="row jb-c-tag-input">
							<div class="col-lg-10">
								<input class="form-control input-lg" id="tag-group" type="text" placeholder="C, C++, java..."
									data-ng-model="pingTags" data-ng-change="traceTagName()">
							</div>
							<div class="col-lg-2">
								<button type="button" class="btn btn-lg btn-primary pull-right"
									data-ng-click="searchUser()">선택</button>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-3">
								<ul class="jb-c-content-list">
									<li data-ng-repeat="userIdsWithTag in userIdsWithTag">
										<div class="input-group">
											<span class="input-group-addon">
												<input type="checkbox">
											</span>
											<div class="form-control">
												{{ userIdsWithTag.nickName }}
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-lg-9">
								<textarea class="form-control" rows="10"
									data-ng-model="pingQuestion"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							data-ng-click="ping($event)">Ping</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="pongModal" tabindex="-1" role="dialog"
			aria-labelledby="Pong" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="pongModalLabel">Pong</h4>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Pong</button>
					</div>
				</div>
			</div>
		</div>
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
