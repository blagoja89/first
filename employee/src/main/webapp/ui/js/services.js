'use strict';

/* Services */

var services = angular.module('ngempl.services', ['ngResource']);
services.factory('UserFactory', function ($resource, $location) {
	
    return $resource('/employee/rest/employees/:itemsPerPage/:page'
    		, {}, {
        query: {
            method: 'GET',
            params: {itemsPerPage: '@itemsPerPage', page: '@page'},
            isArray: false
        }
    });
});
