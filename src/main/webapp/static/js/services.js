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