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
    .factory('genericApi', function (Restangular) {
               Restangular.setBaseUrl('/rest');
               Restangular.addResponseInterceptor(function (data, operation) {
                 if (operation === 'getList' && !_.isArray(data)) {
                   var newResponse = data['entries'];
                   newResponse.entitiesCount = data['totalCount'];
                   newResponse.pageSize = data['pageSize'];
                   newResponse.pagesCount = data['pageCount'];
                   return newResponse;
                 }
                 return data;
               });
               return {
                 request: {
                   getEntityList: function (path, query) {
                     return Restangular.all(path).getList(query);
                   },
                   getEntity: function (path, entryId) {
                     return Restangular.one(path, entryId).get();
                   }
                 }
               };
             })
    .factory('api', function (genericApi) {
               return {
                 blog: {
                   getRecords: function (query) {
                     return genericApi.request.getEntityList('news', query);
                   },
                   getSingleRecord: function (entryId) {
                     return genericApi.request.getEntity('news', entryId);
                   }
                 },
                 organizations: {
                   getFunds: function (query) {
                     return genericApi.request.getEntityList('funds', query);
                   },
                   getPartners: function (query) {
                     return genericApi.request.getEntityList('partners', query);
                   }
                 }
               }
             })
  ;
});