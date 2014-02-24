define(['jquery', 'angular', 'restangular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */
  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('NewsController2', ['$scope', '$routeParams', '$location', 'NewsService', function ($scope, $routeParams, $location, NewsService) {
      $scope.currentPage = 1;
      $scope.entities = NewsService.query({page: $scope.currentPage, limit: 6});
      $scope.totalCount = $scope.entities.length;
      $scope.$watch('currentPage', function (newValue) {
        $scope.entities = NewsService.query({page: newValue, limit: 6});
      });

      $scope.newsEntry = $routeParams.id ? NewsService.get({id: $routeParams.id}) : new NewsService();

      $scope.deleteEntry = function (newsEntry) {
        newsEntry.$remove(function () {
          $scope.pageData = NewsService.get();
        });
      };

      $scope.save = function () {
        $scope.newsEntry.$save(function () {
          $location.path('/');
        });
      };

      /*$scope.goToPage = function (num) {
        $scope.pageData = NewsService.get({page: num});
      };

      $scope.next = function () {
        $scope.pageData = NewsService.get({nav: 'next'});
      };

      $scope.prev = function () {
        $scope.pageData = NewsService.get({nav: 'prev'});
      };*/
    }])
    .controller('NewsController', function($scope, api){
                           $scope.criteria = {
                             page: 1,
                             limit: 10
                           };
                           $scope.fetchResult = function() {
                             return api.news.entries($scope.criteria).then(function(data){
                               console.log(data);
                             }, function(){
                               console.log("nothing");
                             });
                           };
                           $scope.selectPage = function (page) {
                             $scope.criteria.pageNumber = page;
                             $scope.fetchResult();
                           };
                           $scope.selectPage(1);
                         })
    .controller('FundsController', ['$scope', '$routeParams', '$location', 'FundsService', function ($scope, $routeParams, $location, FundsService) {
      $scope.pageData = FundsService.get();
      $scope.organization = $routeParams.id ? FundsService.get({id: $routeParams.id}) : new FundsService();
    }])
    .controller('PartnersController', ['$scope', '$routeParams', '$location', 'PartnersService', function ($scope, $routeParams, $location, PartnersService) {
      $scope.pageData = PartnersService.get();
      $scope.organization = $routeParams.id ? PartnersService.get({id: $routeParams.id}) : new PartnersService();
    }])
    .controller('LoginController',
                ['$scope', '$rootScope', '$location', '$http', '$cookieStore', 'LoginService',
                 function ($scope, $rootScope, $location, $http, $cookieStore, LoginService) {
                   $scope.login = function () {
                     LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function (user) {
                       $rootScope.user = user;
                       $http.defaults.headers.common['X-Auth-Token'] = user.token;
                       $cookieStore.put('user', user);
                       $location.path("/");
                     });
                   };
                 }])
    .controller('NavigationController', ['$scope', '$location', function ($scope, $location) {
      $scope.isCurrentPath = function (viewLocation) {
        return viewLocation === $location.path();
      };
    }]);
});