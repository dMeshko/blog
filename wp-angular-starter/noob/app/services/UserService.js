/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").factory("UserService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.login = function (username, password, remember){
        var data = $.param({
            "username": username,
            "password": password,
            "remember": remember
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        return $http.post(apiURL + "/user/login", data, config);
    };

    factory.signup = function (name, surname, email, username, password){
        var data = $.param({
            "name": name,
            "surname": surname,
            "email": email,
            "username": username,
            "password": password
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        console.log(data);
        return $http.post(apiURL + "/user/signup", data, config);
    };

    return factory;
}]);