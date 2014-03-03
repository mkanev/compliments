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
                    }, function (response) {
                      console.log("Error with status code", response.status);
                    });
                  };
                  $scope.periodicUpdate = function () {
                    $scope.fetchResult();
                    $timeout(function () {
                      $scope.periodicUpdate();
                    }, 60 * 1000); // every minute
                  };
                  $scope.periodicUpdate();
//                  $scope.fetchResult();
                })
});