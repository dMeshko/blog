/**
 * Created by Darko on 3/4/2016.
 */

angular.module("BlogModule", ["angularUtils.directives.dirPagination"]).
    constant("serverURL", "http://localhost:8080/servlet-showcase").
    constant("apiURL", "http://localhost:8080/servlet-showcase/api/blog");