define(['angular', 'app'], function (angular, app) {
    'use strict';

    return app.config([ '$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {

            $routeProvider.when('/create', {
                templateUrl: 'static/partials/create.html',
                controller: 'CreateController'
            });

            $routeProvider.when('/edit/:id', {
                templateUrl: 'static/partials/edit.html',
                controller: 'EditController'
            });

            $routeProvider.when('/login', {
                templateUrl: 'static/partials/login.html',
                controller: 'LoginController'
            });

            $routeProvider.when('/mission', {
                templateUrl: 'static/partials/mission.html'
            });

            $routeProvider.when('/partners', {
                templateUrl: 'static/partials/mission.html'
            });

            $routeProvider.when('/activity', {
                templateUrl: 'static/partials/activity.html'
            });

            $routeProvider.when('/blog', {
                templateUrl: 'static/partials/blog.html'
            });

            $routeProvider.when('/about', {
                templateUrl: 'static/partials/about.html'
            });

            $routeProvider.otherwise({
                                         templateUrl: 'static/partials/index.html',
                                         controller: 'IndexController'
                                     });

            $locationProvider.hashPrefix('!');

            /* Intercept http errors */
            var interceptor = function ($rootScope, $q, $location) {

                function success(response) {
                    return response;
                }

                function error(response) {

                    var status = response.status;
                    var config = response.config;
                    var method = config.method;
                    var url = config.url;

                    if (status == 401) {
                        $location.path("/login");
                    } else {
                        $rootScope.error = method + " on " + url + " failed with status " + status;
                    }

                    return $q.reject(response);
                }

                return function (promise) {
                    return promise.then(success, error);
                };
            };
            $httpProvider.responseInterceptors.push(interceptor);

        }])
        .run(function ($rootScope, $http, $location, $cookieStore, $templateCache, LoginService) {

                 /* Reset error when a new view is loaded */
                 $rootScope.$on('$viewContentLoaded', function () {
                     delete $rootScope.error;
                     $templateCache.removeAll();
                 });

                 $rootScope.hasRole = function (role) {

                     if ($rootScope.user === undefined) {
                         return false;
                     }

                     if ($rootScope.user.roles[role] === undefined) {
                         return false;
                     }

                     return $rootScope.user.roles[role];
                 };

                 $rootScope.logout = function () {
                     delete $rootScope.user;
                     delete $http.defaults.headers.common['X-Auth-Token'];
                     $cookieStore.remove('user');
                     $location.path("/login");
                 };

                 /* Try getting valid user from cookie or go to login page */
                 /*var originalPath = $location.path();
                 $location.path("/login");
                 var user = $cookieStore.get('user');
                 if (user !== undefined) {
                     $rootScope.user = user;
                     $http.defaults.headers.common['X-Auth-Token'] = user.token;

                     $location.path(originalPath);
                 }*/

             });

});