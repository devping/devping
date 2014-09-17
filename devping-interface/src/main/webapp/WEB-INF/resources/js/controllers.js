//mainApp.controller('NavbarController', function ($scope, $location) {
//	$scope.getClass = function (path) {
//		if ($location.path().substr(0, path.length) == path) {
//			return true
//		} else {
//			return false;
//		}
//	}
//});

devPingApp.controller('SummaryController', function ($scope, summaryService) {
	init();
	function init(){
		$scope.summary = summaryService.getSummary();
	};
});

devPingApp.controller('PingSearchController', function($scope, $http, pingSearchService){
	init();
	function init(){
		$scope.tags = pingSearchService.getMoreSelectedTags();
	};
	$scope.selectedTag = function($event, nowSelectedTags, tag){
		$scope.nowSelectedTags = pingSearchService.addTag(nowSelectedTags, tag);
		
		console.log("client request : " + $scope.nowSelectedTags);
		$http.post(
				urlBase+'searchExperts.do',
				'data='+$scope.nowSelectedTags
		).success(function(result){
					console.log(result);
		});
	};
	$scope.searchExperts = function(){
		$scope.proposeExperts = pingSearchService.searchExperts();
	};
});

devPingApp.controller('PingQuestionController', function($scope, summaryService, pingSearchService){
	init();
	function init(){
		$scope.summary = summaryService.getSummary();
		$scope.selectedExperts = pingSearchService.getExpertGroup();
	};
});