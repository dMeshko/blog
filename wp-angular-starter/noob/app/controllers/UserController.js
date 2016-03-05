/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("UserController", ["$scope", "UserService", "serverURL", function ($scope, UserService, serverURL){
    $scope.user = {
        "username": "",
        "password": "",
        "remember": false
    };

    $scope.user1 = {
        "name": "",
        "surname": "",
        "email": "",
        "username": "",
        "password": ""
    };

    $scope.login = function (){
        UserService.login($scope.user.username, $scope.user.password, $scope.user.remember).then(function (response){
            console.log(response.data);
            if (response.data){
                var url = window.location.href;
                var parts = url.split("/");
                var newUrl = "";
                for (i = 0; i < parts.length - 1; i++)
                    newUrl += parts[i] + "/";
                newUrl += "blog.html";
                createCookie("user", true, 1);
                createCookie("userId", response.data.id, 1);
                window.location.href = newUrl;
            }
            else
                alert("LOGIN FAILED!");
        }, function (){
            console.log("error during the login request")
        });
    };

    $scope.signup = function (){
        UserService.signup($scope.user1.name, $scope.user1.surname, $scope.user1.email, $scope.user1.username, $scope.user1.password).then(function (response){
            alert("done");
        }, function (){
            console.log("error creating the user");
        });
    };

    function createCookie(name, value, days) {
        var expires;

        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        } else {
            expires = "";
        }
        document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
    }

    function readCookie(name) {
        var nameEQ = encodeURIComponent(name) + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
        }
        return null;
    }

    function eraseCookie(name) {
        createCookie(name, "", -1);
    }

    $scope.logout = function (){
        eraseCookie("user");
        $scope.showPostButton = false;
        window.location.href = "blog.html";
    }
}]);