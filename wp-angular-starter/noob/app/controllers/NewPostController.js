/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("NewPostController", ["$scope", "BlogService", function ($scope, BlogService){
    $scope.blog = {};
    $scope.title = "";
    $scope.content = "";
    $scope.imageURL = "";
    $scope.userId = 0;
    $scope.categories = [];
    $scope.selectedCategories = {};

    BlogService.getAllCategories(1).then(function (response){
        $scope.categories = response.data;
    }, function (){
        console.log("error creating the categories");
    });

    BlogService.getBlogInfo(1).then(function (response){
        $scope.blog = response.data;
    }, function (){
        console.log("error getting the blog data");
    });

    $scope.addPost = function (){
        BlogService.addPost($scope.title, $scope.content, $scope.imageURL, $scope.userId, $scope.selectedCategories).then(function (){
            window.location.href = "blog.html";
        }, function (){
            console.log("error posting the post!! :D");
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

    if (readCookie("user") === "true"){
        $scope.showPostButton = true;
    }

    $scope.logout = function (){
        eraseCookie("user");
        $scope.showPostButton = false;
        window.location.href = "blog.html";
    };

    $(document).ready(function (){
        $scope.userId = readCookie("userId");
    });

    $scope.testit = function (){
        console.log($scope.selectedCategories);
    };
}]);