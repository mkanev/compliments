require.config(
  {
    baseUrl: "/static/js",
    shim: {
      angular: {
        'exports': 'angular'
      },
      bootstrap: ['jquery'],
      restangular: ['angular', 'lodash'],
      'angular-resource': ['angular'],
      'angular-route': ['angular'],
      'angular-cookies': ['angular'],
      'angular-truncate': ['angular'],
      'angular-ui-bootstrap': ['angular', 'bootstrap'],
      'angular-ui-bootstrap-tpls': ['angular-ui-bootstrap']
    },
    paths: {
      jquery: '/webjars/jquery/2.0.3/jquery.min',
      bootstrap: '/webjars/bootstrap/3.1.1/js/bootstrap.min',
      angular: '/webjars/angularjs/1.2.12/angular.min',
      lodash: '/webjars/lodash/2.4.1/lodash.min',
      restangular: '/webjars/restangular/1.3.1/restangular.min',
      'angular-resource': '/webjars/angularjs/1.2.12/angular-resource.min',
      'angular-route': '/webjars/angularjs/1.2.12/angular-route.min',
      'angular-cookies': '/webjars/angularjs/1.2.12/angular-cookies.min',
      'angular-truncate': '/static/libs/angular-truncate/igTruncate',
      'angular-ui-bootstrap': '/webjars/angular-ui-bootstrap/0.10.0/ui-bootstrap.min',
      'angular-ui-bootstrap-tpls': '/webjars/angular-ui-bootstrap/0.10.0/ui-bootstrap-tpls.min',
      domReady: '/webjars/requirejs-domready/2.0.1/domReady'
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

require(['domReady', 'jquery'], function (domReady) {
  domReady(function () {
    $(".navbar").find("li:not('.dropdown') a").click(function (event) {
      // check if window is small enough so dropdown is created
      $(".navbar-collapse").removeClass("in").addClass("collapse");
    });
  });
});