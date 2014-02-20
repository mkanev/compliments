define(['angular', 'services'], function (angular, services) {
    'use strict';

    /* Filters */

    angular.module('exampleApp.filters', ['exampleApp.services'])
        .filter('interpolate', ['version', function(version) {
            return function(text) {
                return String(text).replace(/\%VERSION\%/mg, version);
            };
        }]);
});