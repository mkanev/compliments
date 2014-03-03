define(['angular', 'app'], function (angular, app) {
  'use strict';

  return app.config(function ($routeProvider, $locationProvider, RestangularProvider) {

    $locationProvider.hashPrefix('!');

    RestangularProvider.setBaseUrl('/rest');
    RestangularProvider.addResponseInterceptor(function (data, operation) {
      if (operation === 'getList' && !_.isArray(data)) {
        var newResponse = data['entries'];
        newResponse.entitiesCount = data['totalCount'];
        newResponse.pageSize = data['pageSize'];
        newResponse.pagesCount = data['pageCount'];
        return newResponse;
      }
      return data;
    });
  })
    .run(function ($rootScope, $http, $location, $cookieStore, $templateCache, Restangular, AuthService) {

           /* Reset error when a new view is loaded */
           $rootScope.$on('$viewContentLoaded', function () {
             delete $rootScope.error;
             $templateCache.removeAll();
           });

           $rootScope.logout = function () {
             AuthService.logout();
           };

           $rootScope.hasRole = function (role) {
             if ($rootScope.user === undefined) {
               return false;
             }
             if ($rootScope.user.roles[role] === undefined) {
               return false;
             }
             return $rootScope.user.roles[role];
           };

           $rootScope.isCurrentPath = function (viewLocation) {
             return viewLocation === $location.path();
           };

           Restangular.setErrorInterceptor(function (response) {
             var status = response.status;
             var config = response.config;
             var method = config.method;
             var url = config.url;

             if (status == 401) {
               $location.path("/login");
             } else {
               $rootScope.error = method + " on " + url + " failed with status " + status;
             }
           });
           /* Try getting valid user from cookie or go to login page */
           var user = $cookieStore.get('user');
           if (user !== undefined) {
             $rootScope.user = user;
             Restangular.setDefaultHeaders({'X-Auth-Token': user.token});
           }

         });

});