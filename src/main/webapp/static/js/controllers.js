define(['jquery', 'angular', 'restangular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */
  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('BlogController', function ($scope, API) {
                  $scope.criteria = {
                    page: 1,
                    limit: 6
                  };
                  $scope.maxSize = 7;
                  $scope.fetchResult = function () {
                    return API.blog.getRecords($scope.criteria).then(function (data) {
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
    .controller('EditRecordController', function ($scope, $location, Restangular, entity) {
                  var original = entity;
                  $scope.newsEntry = Restangular.copy(original);
                  $scope.isBlank = function () {
                    return angular.equals(original, $scope.newsEntry);
                  };
                  $scope.destroy = function () {
                    original.remove().then(function () {
                      $location.path('/blog');
                    });
                  };
                  $scope.save = function () {
                    $scope.newsEntry.put().then(function () {
                      $location.path('/blog');
                    });
                  };
                })
    .controller('CreateRecordController', function ($scope, $location, API) {
                  $scope.save = function () {
                    API.blog.getResource().post($scope.newsEntry).then(function (record) {
                      $location.path('/blog/read/' + record.id);
                    });
                  };
                })
    .controller('FundsController', function ($scope, API) {
                  API.organizations.getFunds().then(function (data) {
                    $scope.entities = data;
                  });
                })
    .controller('PartnersController', function ($scope, API) {
                  API.organizations.getPartners().then(function (data) {
                    $scope.entities = data;
                  });
                })
    .controller('LoginController', function ($scope, AuthService) {
                  $scope.login = function () {
                    AuthService.login($.param({username: $scope.username, password: $scope.password}));
                  };
                })
});