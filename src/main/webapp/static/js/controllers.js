define(['jquery', 'angular', 'services'], function ($, angular) {
    'use strict';

    /* Controllers */

    return angular.module('exampleApp.controllers', ['exampleApp.services'])
        .controller('IndexController', ['$scope', 'NewsService', function ($scope, NewsService) {
            $scope.newsEntries = NewsService.query();

            $scope.deleteEntry = function (newsEntry) {
                newsEntry.$remove(function () {
                    $scope.newsEntries = NewsService.query();
                });
            };
        }])
        .controller('EditController', ['$scope', '$routeParams', '$location', 'NewsService', function ($scope, $routeParams, $location, NewsService) {
            $scope.newsEntry = NewsService.get({id: $routeParams.id});

            $scope.save = function () {
                $scope.newsEntry.$save(function () {
                    $location.path('/');
                });
            };
        }])
        .controller('CreateController', ['$scope', '$location', 'NewsService', function ($scope, $location, NewsService) {
            $scope.newsEntry = new NewsService();

            $scope.save = function () {
                $scope.newsEntry.$save(function () {
                    $location.path('/');
                });
            };
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