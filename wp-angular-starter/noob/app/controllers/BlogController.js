/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("BlogController", ["$scope", "BlogService", "serverURL", function ($scope, BlogService, serverURL){
    $scope.posts = [];
    $scope.blog = {};
    $scope.serverURL = serverURL;
    $scope.currentPage = 1;
    $scope.maxContentLength = 500;
    $scope.postsPerPage = 5;
    $scope.showPostButton = false;

    BlogService.getAllPosts().then(function (response){
        $scope.posts = response.data;
        /*for (i = 0; i < $scope.posts; i++){
            var img = $scope.posts[i].imageUrl[0];
            if (img.indexOf("https") != -1) img = img.substr(img.indexOf("https"), img.length);
        }*/
    }, function (){
        console.log("error getting the posts");
    });


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
    }
}]);