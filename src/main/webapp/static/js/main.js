require.config(
  {
    baseUrl: "/static/js",
    shim: {
      angular: {
        'exports': 'angular'
      },
      bootstrap: ['jquery'],
      restangular: ['angular', 'lodash'],
      'angular-route': ['angular']
    },
    paths: {
      jquery: '/webjars/jquery/2.0.3/jquery.min',
      bootstrap: '/webjars/bootstrap/3.1.1/js/bootstrap.min',
      angular: '/webjars/angularjs/1.2.12/angular.min',
      lodash: '/webjars/lodash/2.4.1/lodash.min',
      restangular: '/webjars/restangular/1.3.1/restangular.min',
      'angular-route': '/webjars/angularjs/1.2.12/angular-route.min'
    },
    deps: ['bootstrap']
  });

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require(['angular', 'app', 'routes'], function (angular, app) {
  'use strict';
  var $html = angular.element(document.getElementsByTagName('html')[0]);

  angular.element().ready(function () {
    angular.resumeBootstrap([app['name']]);
  });
});