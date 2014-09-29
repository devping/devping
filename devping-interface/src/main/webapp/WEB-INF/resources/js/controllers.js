//mainApp.controller('NavbarController', function ($scope, $location) {
//	$scope.getClass = function (path) {
//		if ($location.path().substr(0, path.length) == path) {
//			return true
//		} else {
//			return false;
//		}
//	}
//});
devPingApp.controller('PingPongController', function($scope, pingPongService){
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
		$scope.pingCountdown = 30;
		$scope.pongCount = 0;
	};
	
	$scope.ping = function($event) {
		//set room info
		var dt = new Date();
		var room = {
			time: dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate()
				+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds(),
			tagList: $scope.pingTags,
			question: $scope.pingQuestion
		};
		pingPongService.ping(room, $scope);
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
