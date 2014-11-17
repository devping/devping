devPingApp.service('pingPongService', function($http, $q){
	this.selectTags = function(objTags){
		var deferred = $q.defer();
		$http({
			url: urlBase+'cs/traceTagName.ajax',
			method: "POST",
			data: objTags,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			deferred.resolve(result);
		}).error(function(msg, code){
			deferred.reject(msg);
		});
		return deferred.promise;
	};
	this.searchUser = function(objTags){
		var deferred = $q.defer();
		$http({
			url: urlBase+'cs/searchUser.ajax',
			method: "POST",
			data: objTags,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			deferred.resolve(result);
		}).error(function(msg, code){
			deferred.reject(msg);
		});
		return deferred.promise;
	};
	this.ping = function(objPing){
		var deferred = $q.defer();
		$http({
			url: urlBase+'cs/ping.ajax',
			method: "POST",
			data: objPing,
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			deferred.resolve(result);
		}).error(function(msg, code){
			deferred.reject(msg);
		});
		return deferred.promise;
	};
	this.pong = function(userId){
		var deferred = $q.defer();
		$http({
			url: urlBase+'pong.do',
			method: "POST",
			data: JSON.stringify( userId ),
			headers: {'Content-Type': 'application/json'}
		}).success(function(result){
			deferred.resolve(result);
		}).error(function(msg, code){
			deferred.reject(msg);
		});
		return deferred.promise;
	};
});

devPingApp.factory('MyService', ['$q', '$rootScope', function($q, $rootScope) {
	// We return this object to anything injecting our service
	var Service = {};
	// Keep all pending requests here until they get responses
	var callbacks = {};
	// Create a unique callback ID to map requests to responses
	var currentCallbackId = 0;
	// Create our websocket object with the address to the websocket
	var ws = new WebSocket("ws://devping:8080/echo/");

	ws.onopen = function(){  
		console.log("Socket has been opened!");  
	};

	ws.onmessage = function(message) {
		listener(JSON.parse(message.data));
	};

	function sendRequest(request) {
		var defer = $q.defer();
		var callbackId = getCallbackId();
		callbacks[callbackId] = {
				time: new Date(),
				cb:defer
		};
		request.callback_id = callbackId;
		console.log('Sending request', request);
		ws.send(JSON.stringify(request));
		return defer.promise;
	}

	function listener(data) {
		var messageObj = data;
		console.log("Received data from websocket: ", messageObj);
		// If an object exists with callback_id in our callbacks object, resolve it
		if(callbacks.hasOwnProperty(messageObj.callback_id)) {
			console.log(callbacks[messageObj.callback_id]);
			$rootScope.$apply(callbacks[messageObj.callback_id].cb.resolve(messageObj.data));
			delete callbacks[messageObj.callbackID];
		}
	}
	// This creates a new callback ID for a request
	function getCallbackId() {
		currentCallbackId += 1;
		if(currentCallbackId > 10000) {
			currentCallbackId = 0;
		}
		return currentCallbackId;
	}

	// Define a "getter" for getting customer data
	Service.getCustomers = function() {
		var request = {
				type: "get_customers"
		}
		// Storing in a variable for clarity on what sendRequest returns
		var promise = sendRequest(request); 
		return promise;
	}

	return Service;
}]);
