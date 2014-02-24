define(['angular', 'services'], function (angular, services) {
  'use strict';

  /* Filters */

  angular.module('exampleApp.filters', ['exampleApp.services'])
    .filter('interpolate', ['version', function (version) {
      return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
      };
    }])
    .filter('rangeFrom1', function () {
              return function (input, total) {
                total = parseInt(total);
                for (var i = 0; i < total; i++) {
                  input.push(i+1);
                }
                return input;
              };
            });
});