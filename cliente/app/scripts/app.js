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
        controllerAs: 'main',
        requireLogin : false
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about',
        requireLogin : false
      })
      .when('/cadastro', {
        templateUrl: 'views/cadastro.html',
        controller: 'MainCtrl',
        requireLogin : false

      })
      .when('/horario', {
        templateUrl: 'views/horario.html',
        controller: 'horarioCtrl',
        requireLogin : true
      })
      .when('/cadastroHome', {
        templateUrl: 'views/cadastro.html',
        controller: 'MainCtrl',
        requireLogin : true
      })
      .when('/carona', {
        templateUrl: 'views/carona.html',
        controller: 'horarioCtrl',
        requireLogin : true
      })
      .when('/busca', {
        templateUrl: 'views/busca.html',
        controller: 'buscaCtrl',
        requireLogin : true
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


    //Algoritmo para não deixar algum usario entrar sem estar logado
    if(next.$$route === undefined ||(next.$$route.requireLogin && "Usuario" !== menuService.getMenu())){
          $location.path("/inicio");
    }

  });
});
