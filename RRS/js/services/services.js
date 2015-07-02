/**
 * Created by Prudhvi on 7/1/15.
 */
var services = angular.module('services', []);

services.factory('requestFactory', ['$http', function($http){
    var requestFactory = {};
    requestFactory.getData = function(){
        return $http.get('js/data.json');
    };
    return requestFactory;
}]);

services.factory('editReservationFactory', [function(){
    var editReservationFactory = {};
    var confirmationCode;
    editReservationFactory.getConfirmationCode = function(givenCode){
        confirmationCode = givenCode;
    };
    editReservationFactory.giveConfirmationCode = function(){
        return confirmationCode;
    };
    return editReservationFactory;
}]);