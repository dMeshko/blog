/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("SidebarController", ["$scope", "BlogService", function ($scope, BlogService){
    $scope.categories = [];
    $scope.blog = {};
    $scope.recentPosts = [];

    BlogService.getAllCategories(1).then(function (response){
        $scope.categories = response.data;
    }, function (){
        console.log("error getting the categories");
    });

    BlogService.getBlogInfo(1).then(function (response){
        $scope.blog = response.data;
        $scope.$parent.blog = response.data;
    }, function (){
        console.log("error getting the blog info");
    });

    BlogService.getRecentPosts().then(function (response){
        $scope.recentPosts = response.data;
    }, function (){
        console.log("error getting the recent posts")
    });
}]);