define(['angular', 'app'], function (angular, app) {
  'use strict';

  return app
    .config(function ($routeProvider, $locationProvider, RestangularProvider) {

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
    .run(function () {
           angular.element(document.body).css('opacity', 1);
         });

});