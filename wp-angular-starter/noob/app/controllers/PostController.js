/**
 * Created by Darko on 3/4/2016.
 */

angular.module("BlogModule").controller("PostController", ["$scope", "BlogService", function ($scope, BlogService){
    $scope.post = {};
    $scope.categories = {};
    $scope.blog = {};
    $scope.comments = [];
    $scope.comment = {
        "author": "",
        "content": "",
        "website": "",
        "email": ""
    };
    $scope.callerBtn = {};
    var id = window.location.search.split("id=")[1];
    var author = "";
    var content = "";
    var website = "";
    var email = "";

    BlogService.getPost(id).then(function (response){
        $scope.post = response.data;
        console.log();
    }, function (){
        console.log("error getting the post data");
    });

    var getAllComments = function (){
        BlogService.getAllPostComments(id).then(function (response){
            $scope.comments = response.data;
            //render the comments
            for (i = 0; i < $scope.comments.length; i++) {
                var current = $scope.comments[i];
                var element = $("<li>").attr("id", current.id).addClass("comment").
                    html("<h4>" + current.author + " <small>&bull; " + current.date + "</small> <a href='javascript:void(0)' class='reply-link'>reply</a></h4>" +
                    "<div class='comment-body'><p>" + current.content + "</p></div>" +
                    "<div id='leave-comment' class='hidden lc'><form style='width: 50%;' onsubmit='return false;'><p class='comment-form-author'><label for='author'>Name *</label><input type='text' id='author' data-ng-model = 'comment.author'></p><p class='comment-form-email'><label for='email'>Email *</label><input type='email' id='email' data-ng-model = 'comment.email'></p><p class='comment-form-url'><label for='url'>Website </label><input type='url' id='url'  data-ng-model = 'comment.website'></p><p class='comment-form-comment'><label for='comment'>Comment *</label><textarea id='comment' cols='45' rows='8'  data-ng-model = 'comment.content'></textarea></p><p class='form-submit'><input type='submit' name='submit'></p></form></div><!-- leave-comment -->" +
                    "<ol class='children'></ol>");
                if (current.subComment === null)
                    $(".comments-list").append(element);
                else
                    $("#" + current.subComment.id + ">.children").append(element);
            }
        }, function (){
            console.log("error getting the comments");
        });
    };
    getAllComments();

    $(document).on("click", "form input[type=submit]", function () {
        var parent = $(this).parent().parent().parent().parent();
        var val = $(parent).attr("id");
        if(parent.hasClass("comment") == false)
            val = 0;

        console.log(val);
        $scope.postComment(val);
    }).on("click", ".reply-link", function (){
        $(this).parent().parent().find("#leave-comment").toggleClass("hidden");
    });

    $scope.postComment = function (parent){
        if (parent == 0){
            author = $scope.comment.author;
            content = $scope.comment.content;
            website = $scope.comment.website;
            email = $scope.comment.email;
        }else{
            author = $("#" + parent).find("#leave-comment").find("#author").val();
            content = $("#" + parent).find("#leave-comment").find("#comment").val();
            website = $("#" + parent).find("#leave-comment").find("#website").val();
            email = $("#" + parent).find("#leave-comment").find("#email").val();
        }
        BlogService.postComment(author, content, website, email, id, parent).then(function (response){
            $scope.comment = {
                "author": "",
                "content": "",
                "website": "",
                "email": ""
            };
            $(".comments-list").html("");
            $(".lc").addClass("hidden");
            getAllComments();
        }, function (){
            console.log("Error posting the comment!!");
        });
    };
}]);