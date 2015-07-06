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
            if(!isValid){
                alert("Please Correct the erors!");
            };

            if(isValid){
                requestFactory.postData(self.reservation)
                    .success(function(data){
                        if(data.status === "success"){
                            console.log(data);
                            self.confirmation_code = data.payload.id;
                            self.reservation = {};
                            self.pop_up1 = true;
                        }
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(error){
                        console.log(error);
                        alert("There is an error contacting the server!");
                    });

            };

        };

        self.getReservation = function(isValid){
            var reserveation_match = false;
            if(!isValid){
                alert("Please enter valid confirmation code!");
            };

            if(isValid){
                requestFactory.getData(self.confirmation_code)
                    .success(function (data) {

                        if(data.status === "success"){
                            self.matchedReservation = data.payload;
                            self.pop_up2 = true;
                            console.log("matched reservation", self.matchedReservation);
                        }
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(){
                        alert("request to server has failed!");
                    });
            };
        };

        self.deleteReservation = function(){

            requestFactory.deleteData(self.confirmation_code)
                .success(function(data){
                    if(data.status === "success"){
                        alert("Your Reservation has been cancelled!")
                    }
                    else if(data.status === "error"){
                        console.log(data);
                        alert(data.message);
                    }
                })
                .error(function(data){
                    alert("request to server has failed!");
                })
        };

        self.editReservation = function(){
            console.log('I am in edit scope baby!');
            editReservationFactory.putConfirmationCode(self.matchedReservation.id);
            $location.path('/editReservationDetails');
        };

        self.changeReservation = function(isValid){
            var changingReservationCode = editReservationFactory.getConfirmationCode();
            console.log('I am in change Reservation');
            if(!isValid){
                alert("Please Correct the erors!");
            };
            if(isValid){
                requestFactory.editData(changingReservationCode, self.reservation)
                    .success(function(data){
                        if(data.status === "success"){
                            console.log(data);
                            alert("Your reservation details have been changed successfully!" +
                                " and your confirmation code is same as before. Please check " +
                                "your updated details by entering your confirmation code in the" +
                                " following window inside 'change/cancel reservation' panel.");
                        }
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(error){
                        console.log(error);
                        alert("There is an error contacting the server!");
                    });
            };
            $location.path('/customer');

        };

    }]);

    controllers.controller('OwnerController', ['requestFactory', '$location', function(requestFactory, $location){
        var self = this;
        self.tabNum = 1;
        //get the login credentials from the server
        self.login = {};
        self.loginCredentials = {};
        self.ownerProfile = {};

        self.setTab = function(tabNumber){
            self.tabNum = tabNumber;
        };

        self.isSet = function(tabSelected){
            return self.tabNum === tabSelected;
        };

        self.onLogin = function(isValid){

            if(isValid){

                requestFactory.getLoginInfo()
                    .success(function (data) {
                        if(data.status === "success"){
                            console.log(data);
                            //get the user login credentials from the server
                            self.ownerProfile = data.payload;
                            self.loginCredentials.email = self.ownerProfile.email;
                            self.loginCredentials.password = self.ownerProfile.password;
                            console.log("login credentials", self.loginCredentials);
                            //and check them here
                            if(self.login.email === self.loginCredentials.email){
                                if(self.login.password === self.loginCredentials.password){
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
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(){
                        alert("request to server has failed!");
                    });

            }
            if(!isValid){
                alert("Please correct the errors!")
            }
        };

        self.updateProfile = function(isValid){
            if(isValid){

                requestFactory.updateProfile(self.ownerProfile)
                    .success(function (data) {
                        if(data.status === "success"){
                            alert("Your profile has been udated! " +
                            		"Please login with updated details in the following window");
                            $location.path('ownerLogin');
                        }
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(){
                        alert("request to server has failed!");
                    });

            }

            else if(!isValid){
                alert("Please correct the errors!");
            };
        }


    }]);

})();

