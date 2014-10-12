//mainApp.controller('NavbarController', function ($scope, $location) {
//	$scope.getClass = function (path) {
//		if ($location.path().substr(0, path.length) == path) {
//			return true
//		} else {
//			return false;
//		}
//	}
//});
devPingApp.controller('PingPongController', function($scope, $interval, pingPongService){
	init();
	function init(){
//get user info - require login part
		$scope.user = {
			id: 'guest',
			poing: 0
		};
		$scope.myRoomList = [];
		$scope.chatList = {};
		$scope.userList = {};
		$scope.roomId;
		$scope.pingCountdown = 0;
		$scope.pongCount = 0;
		
		$scope.chatInputChecked = false;
	};
	
	$scope.traceTagName = function() {
		var tagStr = $scope.pingTags;
		var tagArray = tagStr.split(',');
		var objTags = {
				func: "tag_prefix",
				prefix: tagArray[tagArray.length-1].replace(/(^\s*)|(\s*$)/g, "")
		};
		pingPongService.selectTags(objTags, $scope);
	};
	
	$scope.searchUser = function() {
		var tagStr = $scope.pingTags;
		var tagArray = tagStr.split(',');
		for(var i=0, j=tagArray.length; i<j; i++){
			tagArray[i] = tagArray[i].replace(/(^\s*)|(\s*$)/g, "");
		}
		var objTags = {
				func: "tag_people_with_tags",
				tagList: tagArray
		};
		pingPongService.searchUser(objTags, $scope);
	};
	
	$scope.ping = function() {
		var objPing = {
				func: "ping_to_server",
				userIdsWithTag: $scope.userIdsWithTag,
				userId: 'ljhiyh',
				nickName: 'ljhiyh',
				question: $scope.pingQuestion
		};
		pingPongService.ping(objPing, $scope);
		
		//set room timeout
		$scope.pingCountdown = 30;
		//set room info
		var dt = new Date();
		var room = {
			time: dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate()
				+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds(),
			tagList: $scope.pingTags,
			question: $scope.pingQuestion
		};
//		pingPongService.ping(room, $scope);
		
		//visible input box
		$scope.chatInputChecked = true;
		
		$interval(function(){
			$scope.pingCountdown--;
		}, 1000, 30);
	};
	
	$scope.changeRoom = function(roomId){
		$scope.roomId = roomId;
	};
	
	$scope.sendMessage = function(){
		//create message
		var dt = new Date();
		var message = {
			time: dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate()
				+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds(),
			user: 'I',
			content: $scope.myMessage,
			roomId: $scope.roomId
		};
		//send message
		pingPongService.sendMessage(message);
		//input message to chat frame
		$scope.chatList[$scope.roomId].push(message);
	};
	
	$scope.pong = function(){
		pingPongService.pong($scope.user.id);
	};
});
