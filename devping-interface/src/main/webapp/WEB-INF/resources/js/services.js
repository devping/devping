devPingApp.service('pingPongService', function($http){
	this.ping = function(room, $scope){
		$http.post(
				urlBase+'ping.do',
				'data='+room
		).success(function(result){
			//create room
			$scope.roomId = result;
			$scope.chatList[result] = [];
			$scope.userList[result] = [];
			//make room
			room.roomId = result;
			$scope.myRoomList.push(room);
			//log
			console.log($scope.myRoomList);
			console.log($scope.roomId);
		});
	};
	
	this.sendMessage = function(message){
//set user info
		$http.post(
			urlBase+'sendMessage.do',
			'data='+message
		).success(function(result){
			//log
			console.log(result);
		});
	};
	
	this.pong = function(userId){
		$http.post(
				urlBase+'pong.do',
				'data='+userId
		).success(function(result){
			console.log(result);
		});
	};
});
