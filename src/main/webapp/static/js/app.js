define([
           'angular',
           'filters',
           'services',
           'directives',
           'controllers',
           'angular-resource',
           'angular-route',
           'angular-cookies'
       ], function (angular) {
    'use strict';

    // Declare app level module which depends on filters, and services

    return angular.module('exampleApp', [
            'ngRoute',
            'ngCookies',
            'ngResource',
            'exampleApp.controllers',
            'exampleApp.filters',
            'exampleApp.services',
            'exampleApp.directives'
        ])
        .config(function ($logProvider) {
                    $logProvider.debugEnabled(true);
                });
});
