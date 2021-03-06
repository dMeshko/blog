/**
 * Created by Darko on 3/4/2016.
 */

angular.module("BlogModule").factory("BlogService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.getPost = function (id){
        return $http.get(apiURL + "/post/" + id);
    };

    factory.getAllCategories = function (id){
        return $http.get(apiURL + "/category/byBlog/" + id);
    };

    factory.getBlogInfo = function (id){
        return $http.get(apiURL + "/" + id);
    };

    factory.getAllPostComments = function (id){
        return $http.get(apiURL + "/comment/byPost/" + id);
    };

    factory.postComment = function (author, content, website, email, postId, commentId){
        var data = $.param({
            "author": author,
            "content": content,
            "website": website,
            "email": email
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        return $http.post(apiURL + "/comment/add/" + postId + "/" + commentId, data, config);
    };

    factory.getRecentPosts = function (){
        return $http.get(apiURL + "/post/recent");
    };

    factory.getAllPosts = function (){
        return $http.get(apiURL + "/post");
    };

    factory.getAllPostsByCategory = function (categoryId){
        return $http.get(apiURL + "/post/byCategory/" + categoryId);
    };

    factory.getAllPostsByTag = function (tagId){
        return $http.get(apiURL + "/post/byTag/" + tagId);
    };

    factory.search = function (word){
        return $http.get(apiURL + "/post/search?word=" + word);
    };

    factory.addPost = function (title, content, imageURL, authorId){
        var data = $.param({
            "title": title,
            "content": content,
            "imageURL": imageURL,
            "userId": authorId
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        return $http.post(apiURL + "/post/add", data, config);
    };

    return factory;
}]);