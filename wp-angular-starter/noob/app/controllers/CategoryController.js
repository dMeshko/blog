/**
 * Created by Darko on 3/5/2016.
 */

angular.module("BlogModule").controller("CategoryController", ["$scope", "BlogService", function ($scope, BlogService){
    $scope.currentPage = 1;
    $scope.maxContentLength = 50;
    var id = window.location.search.split("id=")[1];

}]);