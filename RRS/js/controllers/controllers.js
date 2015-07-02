/**
 * Created by Prudhvi on 6/28/15.
 */
(function(){

    var controllers = angular.module('controllers', [
        'directives',
        'services'
    ]);

    controllers.controller('CustomerController', ['requestFactory', '$location', 'editReservationFactory', function(requestFactory, $location, editReservationFactory){
        var self = this;
        self.tabNum = 1;
        self.reservation = {};
        //self.matchedReservation = {};
        self.reservationList = [];
        self.confirmation_code = "";
        self.pop_up1 = false;
        self.pop_up2 = false;

        self.setTab = function(tabNumber){
            self.tabNum = tabNumber;
        };

        self.isSet = function(tabSelected){
            return self.tabNum === tabSelected;
        };

        self.makeReservation = function(isValid){
            //once this func is called, the object with "reservation" details has to be posted to the server and
            //a condition has to be set for window popping with corresponding expressions.
            if(!isValid){
                alert("Please Correct the erors!");
            };

            if(isValid){
                alert("Your reservation request is submitted. Please wait for the confirmation status!");
                self.reservation.confirmation_code = "1234";
                self.confirmation_code = self.reservation.confirmation_code;
                self.pop_up1 = true;
                self.reservationList.push(self.reservation);
                console.dir(self.reservationList);
                self.reservation = {};
            };

        };

        self.getReservation = function(isValid){
            //console.dir(this);
            var reserveation_match = false;
            if(!isValid){
                alert("Please enter valid confirmation code!");
            };

            if(isValid){

                requestFactory.getData()
                    .success(function (data) {
                        self.reservationList = data;

                        for(var reservation in self.reservationList){
                            //console.dir(self.reservationList[reservation]);
                            if(self.confirmation_code === self.reservationList[reservation].confirmation_code){
                                reserveation_match = true;
                                self.matchedReservation = self.reservationList[reservation];
                                self.pop_up2 = true;
                                console.dir(self.matchedReservation);
                                break;
                            }
                        }
                        if(reserveation_match === false){
                            alert("There is no reservation with the given confirmation code..");
                            console.dir(self.confirmation_code);
                        }
                    })
                    .error(function(){
                        alert("request to server has failed!");
                    });
            };
        };

        self.deleteReservation = function(){
            console.dir(self.reservationList);
            for(var reservation in self.reservationList){
                if(self.matchedReservation.confirmation_code === self.reservationList[reservation].confirmation_code){
                    self.reservationList.splice(reservation, 1);
                    console.dir(self.reservationList);
                    break;
                }
            }
        };

        self.editReservation = function(){
            console.log('I am in edit scope baby!');
            editReservationFactory.getConfirmationCode(self.matchedReservation.confirmation_code);
            $location.path('/editReservationDetails');
        };

        self.changeReservation = function(isValid){
            var changingReservationCode = editReservationFactory.giveConfirmationCode();
            self.reservation.confirmation_code = changingReservationCode;
            console.log('I am in change Reservation');
            if(isValid){

                requestFactory.getData()
                    .success(function (data) {
                        self.reservationList = data;
                        for(var reservation in self.reservationList){
                            //console.dir(self.reservationList[reservation]);
                            if(self.reservation.confirmation_code === self.reservationList[reservation].confirmation_code){
                                console.dir(self.reservationList);
                                self.reservationList[reservation] = self.reservation;
                                console.dir(self.reservationList);
                                alert("Your Reservation Details are now changed with same confirmation code");
                                break;
                            }
                        }
                        $location.path('/customer');
                    })
                    .error(function(){
                        alert("request to server has failed!");
                    });
            };

        };

    }]);

    controllers.controller('OwnerController', ['$location', function($location){
        var ownerCtrl = this;
        ownerCtrl.tabNum = 1;
        //get the login credentials from the server
        ownerCtrl.login = {};
        ownerCtrl.loginCredentials = {
            email: "prudhvi.af121@gmail.com",
            password: "Qwerty12#"
        };

        ownerCtrl.setTab = function(tabNumber){
            ownerCtrl.tabNum = tabNumber;
        };

        ownerCtrl.isSet = function(tabSelected){
            return ownerCtrl.tabNum === tabSelected;
        };

        ownerCtrl.onLogin = function(isValid){
            //get the user login credentials from the server and check them here
            if(isValid){
                if(ownerCtrl.login.email === ownerCtrl.loginCredentials.email){
                    if(ownerCtrl.login.password === ownerCtrl.loginCredentials.password){
                        $location.path('/owner');
                    }
                    else{
                        alert("The password is incorrect!");
                    }
                }
                else{
                    alert("The email is incorrect")
                }
            }
            if(!isValid){
                alert("Please correct the errors!")
            }
        };


    }]);

})();

