define([
         'angular',
         'filters',
         'services',
         'directives',
         'controllers',
         'angular-route',
         'restangular'
       ], function (angular) {
  'use strict';

  // Declare app level module which depends on filters, and services

  return angular.module('exampleApp', [
    'ngRoute',
    'restangular',
    'exampleApp.controllers',
    'exampleApp.filters',
    'exampleApp.services',
    'exampleApp.directives'
  ]);
});
