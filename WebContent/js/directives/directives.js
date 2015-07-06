/**
 * Created by Prudhvi on 6/29/15.
 */
(function(){

    var directives = angular.module('directives', []);

    directives.directive('reservationsTab', function(){
        var obj = {};
        obj = {
            restrict:'E',
            templateUrl: 'partials/owner-tabs/reservations-tab.html'
        };
        console.dir(obj);
        return obj;
    });

    directives.directive('seatingTab', function(){
        var obj = {};
        obj = {
            restrict:'A',
            templateUrl: 'partials/owner-tabs/seating-tab.html'
        };
        console.dir(obj);
        return obj;
    });

    directives.directive('profileTab', function(){
        var obj = {};
        obj = {
            restrict:'AE',
            templateUrl: 'partials/owner-tabs/profile-tab.html'
        };
        return obj;
    });

    directives.directive('settingsTab', function(){
        var obj = {};
        obj = {
            restrict:'AE',
            templateUrl: 'partials/owner-tabs/settings-tab.html'
        };
        console.dir(obj);
        return obj;
    });

    directives.directive('contactsTab', function(){
        var obj = {};
        obj = {
            restrict:'AE',
            templateUrl: 'partials/owner-tabs/contacts-tab.html'
        };
        console.dir(obj);
        return obj;
    });

    directives.directive("makeReservationPopUp", function(){
        var ddo;
        ddo = {
            restrict: 'AE',

            scope: {confirmationCode: '@confirmationCode',
            	reservationStatus: '@reservationStatus',
                show: '=show'},

            transclude: false,

            link: function(scope, element, attrs) {
                scope.dialogStyle = {};
                if (attrs.width)
                    scope.dialogStyle.width = attrs.width;
                if (attrs.height)
                    scope.dialogStyle.height = attrs.height;
                scope.hideModal = function() {
                    scope.show = false;
                };
            },

            templateUrl: 'partials/makeReservationPopUp.html',
        };
        return ddo;
    });

    directives.directive("getReservationPopUp", function(){
        var ddo;
        ddo = {
            restrict: 'AE',

            scope: {matchedReservation: '=matchedReservation',
            show: '=show',
            deleteReservation: '&deleteReservation',
            editReservation: '&editReservation'},

            transclude: false,

            link: function(scope, element, attrs) {
                scope.dialogStyle = {};
                if (attrs.width)
                    scope.dialogStyle.width = attrs.width;
                if (attrs.height)
                    scope.dialogStyle.height = attrs.height;
                scope.hideModal = function() {
                    scope.show = false;
                };
            },

            templateUrl: 'partials/getReservationPopUp.html',
        };
        return ddo;
    });

})();