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
                 getListEndpoint: function (path) {
                   return Restangular.all(path);
                 },
                 getEntityEndpoint: function (path, entryId) {
                   return Restangular.one(path, entryId);
                 }
               };
             })
    .factory('genericEntityApi', function (genericApi) {
               return {
                 getEntityList: function (path, query) {
                   return genericApi.getListEndpoint(path).getList(query);
                 },
                 getEntity: function (path, entryId) {
                   return genericApi.getEntityEndpoint(path, entryId).get();
                 }
               };
             })
    .factory('api', function (genericEntityApi) {
               return {
                 blog: {
                   getRecords: function (query) {
                     return genericEntityApi.getEntityList('news', query);
                   },
                   getSingleRecord: function (entryId) {
                     return genericEntityApi.getEntity('news', entryId);
                   }
                 },
                 organizations: {
                   getFunds: function (query) {
                     return genericEntityApi.getEntityList('funds', query);
                   },
                   getPartners: function (query) {
                     return genericEntityApi.getEntityList('partners', query);
                   }
                 }
               }
             })
    .factory('authService', function ($rootScope, Restangular, genericApi) {
               var user = {
                 isAuthenticated: false,
                 name: ''
               };
               $rootScope.user = user;
               var authService = {};
               authService.init = function (isAuthenticated, userName) {
                 user.isAuthenticated = isAuthenticated;
                 user.name = userName;
               };
               authService.isAuthenticated = function () {
                 return user.isAuthenticated;
               };
               authService.login = function (loginModel) {
                 var loginResult = genericApi.getListEndpoint('user').customPOST(loginModel, 'login');
                 loginResult.then(function (result) {
                   user.isAuthenticated = result.loginOk;
                   if (result.loginOk) {
                     user.name = loginModel.userName;
                   }
                 });
                 return loginResult;
               };
               authService.logout = function () {
                 return genericApi.getListEndpoint('user').customPOST(null, 'logout')
                   .then(function (result) {
                           user.isAuthenticated = false;
                           user.name = '';
                         });
               };
               authService.register = function (registerModel) {
                 return genericApi.getListEndpoint('user').customPOST(registerModel, 'register');
               };
               authService.changePassword = function (changePasswordModel) {
                 return genericApi.getListEndpoint('user').customPUT(changePasswordModel, 'changePassword');
               };
               return authService;

             })
  ;
});