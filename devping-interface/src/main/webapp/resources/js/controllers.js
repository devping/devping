//devPingApp.controller('customerList', ['MyService', function(MyService){
//	$scope.customers = MyService.getCustomers();
//}]);

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
		$scope.pingCountdown = {};
		$scope.channelId;
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
		var promise = pingPongService.selectTags(objTags);
		promise.then(
			function(payload) {
				$scope.tagList = payload.tagList;
			},
			function(errorPayload) {
				console.log(errorPayload);
			});
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
		var promise = pingPongService.searchUser(objTags);
		promise.then(
			function(payload) {
				$scope.userIdsWithTag = payload.userIdsWithTag;
				$scope.totalMembers = payload.totalMembers;
			},
			function(errorPayload) {
				console.log(errorPayload);
			});
	};
	
	$scope.ping = function() {
//set user info
		var objPing = {
				func: "ping_to_server",
				userIdsWithTag: $scope.userIdsWithTag,
				userId: '',
				nickName: '',
				question: $scope.pingQuestion
		};
		var promise = pingPongService.ping(objPing);
		promise.then(
			function(payload) {
				//create room
				$scope.channelId = payload.channelId;
				$scope.chatList[payload.channelId] = [];
				$scope.userList[payload.channelId] = [];
				var dt = new Date();
				var room = {
					channelId: payload.channelId,
					time: dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate()
						+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds(),
					tagList: $scope.pingTags,
					pingCountdown : 30
				};
				$interval(function(){
					room.pingCountdown--;
//if someone connect this room, stop countdown.
				}, 1000, 30);
				//make room
				$scope.myRoomList.push(room);
			},
			function(errorPayload) {
				console.log(errorPayload);
			});
		//visible input box
		$scope.chatInputChecked = true;
	};
	
	$scope.changeRoom = function(channelId){
		$scope.channelId = channelId;
	};
	
	$scope.sendMessage = function(){
		//create message
		var dt = new Date();
		var message = {
			time: dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate()
				+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds(),
			user: 'I',
			content: $scope.myMessage,
			channelId: $scope.channelId
		};
		//send message
		pingPongService.sendMessage(message);
		//input message to chat frame
		$scope.chatList[$scope.channelId].push(message);
	};
	
	$scope.pong = function(){
		pingPongService.pong($scope.user.id);
	};
});
