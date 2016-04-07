'use strict';

/**
 * @ngdoc overview
 * @name clienteApp
 * @description
 * # clienteApp
 *
 * Main module of the application.
 */
angular
  .module('clienteApp', [
    'ngAnimate',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/cadastro', {
        templateUrl: 'views/cadastro.html',
        controller: 'MainCtrl',

      })
      .when('/horario', {
        templateUrl: 'views/horario.html',
        controller: 'horarioCtrl'
      })
      .when('/cadastroHome', {
        templateUrl: 'views/cadastro.html',
        controller: 'MainCtrl',

      })
      .when('/busca', {
        templateUrl: 'views/busca.html',
        controller: 'buscaCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });


angular.module("clienteApp").run(function($rootScope, $location, menuService, mainService){
  $rootScope.$on('$routeChangeStart', function(event, next, current) {

    //Logout -> Deve haver soluções melhores
    if($location.path() === "/sair"){
      menuService.setMenu("inicio");
      mainService.logout();
      $location.path("/");
    }

  });
});
