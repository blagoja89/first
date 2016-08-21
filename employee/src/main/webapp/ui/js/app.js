
// Declare app level module which depends on filters, and services
angular.module('ngempl', ['ngempl.services', 'ngempl.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/employees/:itemsPerPage/:page', {templateUrl: 'ui/page/employeeList.html', controller: 'MyCtrl1'});
        
        $routeProvider.otherwise({redirectTo: '/employees/:itemsPerPage/:page'});
    }]);
