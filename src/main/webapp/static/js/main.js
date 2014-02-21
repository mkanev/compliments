require.config(
    {
        baseUrl: "/static/js",
        shim: {
            angular: {
                'exports': 'angular'
            },
            bootstrap: ['jquery'],
            'angular-resource': ['angular'],
            'angular-route': ['angular'],
            'angular-cookies': ['angular']
        },
        paths: {
            jquery: '/webjars/jquery/2.0.3/jquery',
            bootstrap: '/webjars/bootstrap/3.1.1/js/bootstrap',
            angular: '/webjars/angularjs/1.2.9/angular',
            'angular-resource': '/webjars/angularjs/1.2.9/angular-resource',
            'angular-route': '/webjars/angularjs/1.2.9/angular-route',
            'angular-cookies': '/webjars/angularjs/1.2.9/angular-cookies'
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