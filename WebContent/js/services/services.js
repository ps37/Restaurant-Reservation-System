/**
 * Created by Prudhvi on 7/1/15.
 */
var services = angular.module('services', []);

services.factory('requestFactory', ['$http', function($http){
    var requestFactory = {};
    requestFactory.getData = function(cc){
        console.log(typeof cc, cc);
        return $http.get('api/reservations/get/' + cc);
    };
    requestFactory.postData = function(data){
        console.dir(data);
        return $http.post('api/reservations/create', data);
    };
    requestFactory.deleteData = function(cc){
        console.dir(cc);
        return $http.post('api/reservations/delete/' + cc);
    };
    requestFactory.editData = function(cc, data){
        console.log(cc, data);
        return $http.post('api/reservations/edit/' + cc, data);
    };

    requestFactory.getLoginInfo = function(){
    	console.log('i am in request factory!');
        return $http.get('api/profiles/get/loginInfo');
    };
    requestFactory.updateProfile = function(data){
        return $http.post('api/profiles/update/loginProfile', data);
    };
    return requestFactory;
}]);

services.factory('editReservationFactory', [function(){
    var editReservationFactory = {};
    var confirmationCode;
    editReservationFactory.putConfirmationCode = function(givenCode){
        confirmationCode = givenCode;
    };
    editReservationFactory.getConfirmationCode = function(){
        return confirmationCode;
    };
    return editReservationFactory;
}]);