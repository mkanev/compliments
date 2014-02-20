define(['angular'], function (angular) {
    'use strict';

    /* Services */
    angular.module('exampleApp.services', [])
        .factory('LoginService', function ($resource) {
                     return $resource('rest/user/:action', {},
                                      {
                                          authenticate: {
                                              method: 'POST',
                                              params: {'action': 'authenticate'},
                                              headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                                          }
                                      }
                     );
                 })
        .factory('NewsService', function ($resource) {
                     return $resource('rest/news/:id', {id: '@id'});
                 });
});