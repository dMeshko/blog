/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("BlogController", ["$scope", "BlogService", "serverURL", function ($scope, BlogService, serverURL){
    $scope.posts = [];
    $scope.blog = {};
    $scope.serverURL = serverURL;
    $scope.currentPage = 1;
    $scope.maxContentLength = 50;

    BlogService.getAllPosts().then(function (response){
        $scope.posts = response.data;
    }, function (){
        console.log("error getting the posts");
    });
}]);