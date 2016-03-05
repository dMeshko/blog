var WPAngularStarter = angular.module('wp-angular-starter', [
  'ui.router',
  'ngResource',
  'pascalprecht.translate',
  'smart-table',
  'mgcrea.ngStrap',
  'toastr',
  'angular-loading-bar',
  'ui.select',
  'ngQuickDate']);

  WPAngularStarter.constant("serverURL", "http://localhost:8080/servlet-showcase");
  WPAngularStarter.constant("apiURL", "http://localhost:8080/servlet-showcase/api/blog");

  WPAngularStarter.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider){
    $stateProvider.state("app", {
      url: "/",
      views: {
        "header": {
          templateUrl: "/views/header.html"
        },
        "content": {
          templateUrl: "/views/content.html",
          controller: "PostController"
        },
        "sidebar": {
          templateUrl: "/views/sidebar.html"
        },
        "footer": {
          templateUrl: "/views/footer.html"
        }
      }
    });

    //handle undefiend states, send them on the home page..we don't have 404..
    $urlRouterProvider.otherwise("/");
  }]);

