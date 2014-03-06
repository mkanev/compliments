define(['jquery', 'angular', 'restangular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */
  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('ComplimentsController', function ($scope, $timeout, Restangular) {
                  $scope.entity = 'Zагружаю :)';
                  $scope.fetchResult = function () {
                    return Restangular.all('compliment').customGET('random').then(function (data) {
                      $scope.entity = data;
                    }, function (response) {
                      console.log("Error with status code", response.status);
                    });
                  };
                  $scope.periodicUpdate = function () {
                    $scope.fetchResult();
                    $timeout(function () {
                      $scope.periodicUpdate();
                    }, 20 * 1000); // every 20 sec
                  };
                  $scope.periodicUpdate();
                })
});