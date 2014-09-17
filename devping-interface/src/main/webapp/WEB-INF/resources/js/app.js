//setInterval(function () {alert("Hello")}, 3000);

var urlBase = "/devping/"; 

var devPingApp = angular.module('devPingApp', ['ngRoute']);

devPingApp.config(function ($routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$routeProvider
		.when('/main',
			{
				controller: 'PingSearchController',
				templateUrl: urlBase + 'ping-search'
			})
		.when('/write',
			{
				controller: 'PingQuestionController', 
				templateUrl: urlBase + 'ping-write'
			})
		.otherwise({ redirectTo: '/main' });
});