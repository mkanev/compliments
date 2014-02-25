define(['jquery', 'angular', 'restangular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */
  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('BlogController', function ($scope, $routeParams, api) {
                  $scope.criteria = {
                    page: 1,
                    limit: 6
                  };
                  $scope.maxSize = 7;
                  $scope.fetchResult = function () {
                    return api.blog.getRecords($scope.criteria).then(function (data) {
                      $scope.entities = data;
                      $scope.entitiesCount = data.entitiesCount;
                      $scope.pageSize = data.pageSize;
                      $scope.pagesCount = data.pagesCount;
                    }, function (response) {
                      console.log("Error with status code", response.status);
                    });
                  };
                  $scope.selectPage = function (page) {
                    $scope.criteria.page = page;
                    $scope.fetchResult();
                  };
                  $scope.selectPage(1);
                })
    .controller('BlogRecordController', function ($scope, $location, Restangular, api, entity) {
                  var original = entity;
                  $scope.newsEntry = Restangular.copy(original);
                  $scope.isBlank = function () {
                    return angular.equals(original, $scope.newsEntry);
                  };
                  $scope.destroy = function () {
                    original.remove().then(function () {
                      $location.path('#!/blog');
                    });
                  };
                  $scope.save = function () {
                    $scope.newsEntry.put().then(function () {
                      $location.path('#!/blog');
                    });
                  };
                })
    .controller('FundsController', function ($scope, $routeParams, api) {
                  api.organizations.getFunds().then(function (data) {
                    $scope.entities = data;
                  });
                })
    .controller('PartnersController', function ($scope, $routeParams, api) {
                  api.organizations.getPartners().then(function (data) {
                    $scope.entities = data;
                  });
                })
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
});