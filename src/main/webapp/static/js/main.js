require.config(
  {
    baseUrl: "/static/js",
    shim: {
      angular: {
        exports: 'angular'
      },
      bootstrap: ['jquery'],
      restangular: ['angular', 'lodash'],
      'angular-route': ['angular'],
      'google-analytics': {
        exports: 'ga'
      }
    },
    paths: {
      jquery: '/webjars/jquery/2.0.3/jquery.min',
      bootstrap: '/webjars/bootstrap/3.1.1/js/bootstrap.min',
      angular: '/webjars/angularjs/1.2.12/angular.min',
      lodash: '/webjars/lodash/2.4.1/lodash.min',
      restangular: '/webjars/restangular/1.3.1/restangular.min',
      'angular-route': '/webjars/angularjs/1.2.12/angular-route.min',
      'google-analytics': '//www.google-analytics.com/analytics'
    },
    deps: ['bootstrap']
  });

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require(['angular', 'app', 'ga', 'google-analytics', 'routes'], function (angular, app, gaApi, ga) {
  'use strict';
  var $html = angular.element(document.getElementsByTagName('html')[0]);

  ga('create', 'UA-48616105-1', {
    'cookieDomain': 'none'
  });
  ga('send', 'pageview');
  gaApi.trackTiming("webapp", "initialise")

  angular.element().ready(function () {
    angular.resumeBootstrap([app['name']]);
  });
});