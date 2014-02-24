define(['angular', 'restangular'], function (angular) {
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
               return $resource('rest/news/:id', {
                 id: '@id',
                 page: '@page',
                 limit: '@limit',
                 nav: '@nav'
               });
             })
    .factory('FundsService', function ($resource) {
               return $resource('rest/funds/:id', {id: '@id'});
             })
    .factory('PartnersService', function ($resource) {
               return $resource('rest/partners/:id', {id: '@id'});
             })
    .factory('api', function (Restangular) {
               Restangular.setBaseUrl('/rest');
               return {
                 news: {
                   entries: function (query) {
                     return Restangular.all("news").getList(query);
                   }
                 }
               };
             })
  ;
});