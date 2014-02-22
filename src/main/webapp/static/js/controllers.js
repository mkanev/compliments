define(['jquery', 'angular', 'services'], function ($, angular) {
  'use strict';

  /* Controllers */

  return angular.module('exampleApp.controllers', ['exampleApp.services'])
    .controller('NewsController', ['$scope', '$routeParams', '$location', 'NewsService', function ($scope, $routeParams, $location, NewsService) {
      $scope.newsEntries = NewsService.query();

      $scope.newsEntry = $routeParams.id ? NewsService.get({id: $routeParams.id}) : new NewsService();

      $scope.deleteEntry = function (newsEntry) {
        newsEntry.$remove(function () {
          $scope.newsEntries = NewsService.query();
        });
      };

      $scope.save = function () {
        $scope.newsEntry.$save(function () {
          $location.path('/');
        });
      };
    }])
    .controller('OrganizationsController', ['$scope', '$routeParams', '$location', 'OrganizationsService', function ($scope, $routeParams, $location, OrganizationsService) {
      $scope.funds = OrganizationsService.getFunds();
      $scope.partners = OrganizationsService.getPartners();

      $scope.organization = $routeParams.id ? OrganizationsService.get({id: $routeParams.id}) : new OrganizationsService();
    }])
    .controller('LoginController',
                ['$scope', '$rootScope', '$location', '$http', '$cookieStore', 'LoginService',
                 function ($scope, $rootScope, $location, $http, $cookieStore, LoginService) {
                   $scope.login = function () {
                     LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function (user) {
                       $rootScope.user = user;
                       $http.defaults.headers.common['X-Auth-Token'] = user.token;
                       $cookieStore.put('user', user);
                       $location.path("/");
                     });
                   };
                 }])
    .controller('NavigationController', ['$scope', '$location', function ($scope, $location) {
      $scope.isCurrentPath = function (viewLocation) {
        return viewLocation === $location.path();
      };
    }]);
});