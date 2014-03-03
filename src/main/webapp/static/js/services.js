define(['angular', 'restangular'], function (angular) {
  'use strict';

  /* Services */
  angular.module('exampleApp.services', [])
    .factory('GenericApi', function ($rootScope, Restangular) {
               return {
                 getEndpoint: function (path) {
                   return Restangular.all(path);
                 },
                 getEntityEndpoint: function (path, entryId) {
                   return Restangular.one(path, entryId);
                 }
               };
             })
    .factory('AuthService', function ($rootScope, $cookieStore, $location, $http, Restangular, GenericApi) {
               return {
                 login: function (authData) {
                   GenericApi.getEndpoint('user')
                     .customPOST(authData, 'authenticate', {}, {'Content-Type': 'application/x-www-form-urlencoded'})
                     .then(function (userData) {
                             $rootScope.user = userData;
                             $cookieStore.put('user', userData);
                             Restangular.setDefaultHeaders({'X-Auth-Token': userData.token});
                             $location.path("/");
                           });
                 },
                 logout: function () {
                   delete $rootScope.user;
                   delete Restangular.defaultHeaders['X-Auth-Token'];
                   $cookieStore.remove('user');
                 }
               }
             })
    .factory('GenericEntityApi', function ($rootScope, GenericApi) {
               return {
                 getEntityResource: function (path) {
                   return GenericApi.getEndpoint(path);
                 },
                 getEntityList: function (path, query) {
                   return GenericApi.getEndpoint(path).getList(query);
                 },
                 getEntity: function (path, entryId) {
                   return GenericApi.getEntityEndpoint(path, entryId).get();
                 }
               };
             })
    .factory('API', function (GenericApi, GenericEntityApi) {
               return {
                 compliment: {
                   getRecords: function (query) {
                     return GenericEntityApi.getEntityList('compliment', query);
                   }
                 }
               }
             })

  ;
});