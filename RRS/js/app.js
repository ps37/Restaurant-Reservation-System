/**
 * Created by Prudhvi on 6/28/15.
 */
(function(){
    var app = angular.module('rrs', [
        'ngRoute',
        'ngMessages',
        'ngAnimate',
        'controllers'
    ]);

    app.config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/initial', {
                templateUrl: 'partials/initial.html',
                //controller: 'InitialController'
            })//when initial

            .when('/customer', {
                templateUrl: 'partials/customer.html',
                controller: 'CustomerController',
                controllerAs: 'customerCtrl'
            })//when cusomer

            .when('/ownerLogin', {
                templateUrl: 'partials/ownerLogin.html',
                controller: 'OwnerController',
                controllerAs: 'ownerCtrl'
            })//when owner

            .when('/owner', {
                templateUrl: 'partials/owner.html',
                controller: 'OwnerController',
                controllerAs: 'ownerCtrl1'
            })//when owner

            .when('/editReservationDetails', {
                templateUrl: 'partials/editReservationDetails.html',
                controller: 'CustomerController',
                controllerAs: 'customerCtrl1'
            })//when owner

            .otherwise({
                redirectTo: '/initial'
            })//otherwise

    }])//app.config

})();//IIFE