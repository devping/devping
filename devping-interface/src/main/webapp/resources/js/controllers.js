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
		$scope.channelId;
		$scope.pongCount = 0;
		$scope.chatInputChecked = false;
	};
	
	$scope.traceTagName = function() {
		var tagArray = $scope.pingTags.split(',');
		pingPongService.selectTags({
			func: "tag_prefix",
			prefix: tagArray[tagArray.length-1].replace(/(^\s*)|(\s*$)/g, "")
		}).then(
			function(payload) {
				$scope.tagList = payload.tagList;
			},
			function(errorPayload) {
				console.log(errorPayload);
			});
	};
	
	$scope.searchUser = function() {
		var tagArray = $scope.pingTags.split(',');
		for(var i=0, j=tagArray.length; i<j; i++){
			tagArray[i] = tagArray[i].replace(/(^\s*)|(\s*$)/g, "");
		}
		pingPongService.searchUser({
			func: "tag_people_with_tags",
			tagList: tagArray
		}).then(
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
		pingPongService.ping({
			func: "ping_to_server",
			userIdsWithTag: $scope.userIdsWithTag,
			userId: 'ljhiyh',
			nickName: 'ljhiyh',
			question: $scope.pingQuestion
		}).then(
			function(payload) {
				//create room
				$scope.channelId = payload.channelId;
				$scope.chatList[payload.channelId] = [];
				$scope.userList[payload.channelId] = $scope.userIdsWithTag;
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
					if(room.pingCountdown == 0)
						removeDevpinRoom(room.channelId);
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
		changeDevpingRoom(channelId);
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
	
	function changeDevpingRoom(channelId){
		$scope.channelId = channelId;
	};
	
	function removeDevpinRoom(channelId){
		for(var i=0,j=$scope.myRoomList.length;i<j;i++){
			var room =  $scope.myRoomList[i];
			if(room.channelId == channelId)
				$scope.myRoomList.splice(i, 1);
		}
		delete $scope.chatList[channelId];
		delete $scope.userList[channelId];
		if($scope.channelId == channelId)
			$scope.channelId = '';
	};
});