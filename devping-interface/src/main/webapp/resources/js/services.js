devPingApp.service('pingPongService', function($http){
	this.selectTags = function(objTags, $scope) {
		$http({
			url: urlBase+'/test/traceTagName.ajax',
			method: "POST",
			data: objTags,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			console.log(result);
			$scope.tagList = result.tagList;
		}).error(function (error) {
			console.log(error);
		});
	};
	this.searchUser = function(objTags, $scope) {
		$http({
			url: urlBase+'/test/searchUser.ajax',
			method: "POST",
			data: objTags,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			console.log(result);
			$scope.userIdsWithTag = result.userIdsWithTag;
			$scope.totalMembers = result.totalMembers;
		}).error(function (error) {
			console.log(error);
		});
	};
	this.ping = function(objPing, $scope){
		$http({
			url: urlBase+'/test/ping.ajax',
			method: "POST",
			data: objPing,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			//create room
			$scope.roomId = result.channelId;
			$scope.chatList[result.channelId] = [];
			$scope.userList[result.channelId] = [];
			//make room
//			room.roomId = result.channelId;
//			$scope.myRoomList.push(room);
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
