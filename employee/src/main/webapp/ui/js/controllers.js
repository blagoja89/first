/* Controllers */
var app = angular.module('ngempl.controllers', []);

// Clear browser cache (in development mode)
app.run(function($rootScope, $templateCache) {
	$rootScope.$on('$viewContentLoaded', function() {
		$templateCache.removeAll();
	});
});
var currentPage = 1;
var pageSize = 100;
app.controller('MyCtrl1', ['$scope', '$http', 'UserFactory', '$location', function($scope, $http, UserFactory, $location) {
	$scope.currentPage = currentPage;
	$scope.pageSize = pageSize;
	currentPage = $scope.currentPage;
	pageSize = $scope.pageSize;
	$scope.buffer = [];
	
	UserFactory.get({ itemsPerPage: $scope.pageSize, page: $scope.currentPage }, function (userFactory) {
		$scope.buffer = userFactory.employees;
	});
	
	$scope.pageChangeHandler = function(num) {
		$.ajax({  
          url: "/employee/rest/employees/" +  $scope.pageSize + "/" + $scope.currentPage,  
          cache: false, 
          success: function(data){  
        	  $scope.buffer = data.employees;
          }  
		});  
		console.log('employees page changed to ' + num);
	};
	
	var updateEmployees = function() {
	    $scope.employees = $scope.buffer;
	  };
	  setInterval(function() {
	    $scope.$apply(updateEmployees);
	  }, 1);
	  updateEmployees();
	
		} ]);






