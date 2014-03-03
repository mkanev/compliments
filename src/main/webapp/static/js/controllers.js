define(['jquery', 'angular', 'restangular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */
  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('ComplimentsController', function ($scope, $timeout, API) {
                  $scope.criteria = {
                    navigate: 'next',
                    limit: 1
                  };
                  $scope.fetchResult = function () {
                    return API.compliment.getRecords($scope.criteria).then(function (data) {
                      $scope.entities = data;
                      $timeout(function () {
                        $scope.fetchResult();
                      }, 1000);
                    }, function (response) {
                      console.log("Error with status code", response.status);
                    });
                  };
                  $scope.fetchResult();
                })
});