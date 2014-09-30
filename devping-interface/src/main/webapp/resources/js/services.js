devPingApp.service('pingPongService', function($http){
	this.ping = function(room, $scope){
		$http({
			url: urlBase+'ping.do',
			method: "POST",
			data: JSON.stringify( room ),
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
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
		}).error(function (error) {
			console.log(error);
		});
	};
	
	this.sendMessage = function(message){
//set user info
		$http({
			url: urlBase+'sendMessage.do',
			method: "POST",
			data: JSON.stringify( message ),
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			//log
			console.log(result);
		}).error(function (error) {
			console.log(error);
		});
	};
	
	this.pong = function(userId){
		$http({
			url: urlBase+'pong.do',
			method: "POST",
			data: JSON.stringify( userId ),
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			//log
			console.log(result);
		}).error(function (error) {
			console.log(error);
		});
	};
});
