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
    'ui.bootstrap',
    'angularUtils.directives.dirPagination',
    'ngFileUpload'

  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/inicio/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main',
        requireLogin : false
      })
      .when('/about', {
        templateUrl: 'views/inicio/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about',
        requireLogin : false
      })
      .when('/team', {
        templateUrl: 'views/inicio/team.html',
        controller: 'teamCtrl',
        requireLogin: false
      })
      .when('/cadastro', {
        templateUrl: 'views/inicio/cadastro.html',
        controller: 'MainCtrl',
        requireLogin : false

      })
      .when('/home', {
        templateUrl: 'views/usuario/home.html',
        controller: 'homeCtrl',
        requireLogin : false

      })
      .when('/horario', {
        templateUrl: 'views/usuario/horario.html',
        controller: 'horarioCtrl',
        requireLogin : false
      })
      .when('/cadastroHome', {
        templateUrl: 'views/usuario/cadastro.html',
        controller: 'MainCtrl',
        requireLogin : true
      })
      .when('/carona', {
        templateUrl: 'views/usuario/carona.html',
        controller: 'caronaCtrl',

        requireLogin : false
      })
      .when('/buscar', {
        templateUrl: 'views/usuario/busca.html',
        controller: 'buscaCtrl',
        requireLogin : false
      })
      .when('/notificacao', {
        templateUrl: 'views/usuario/notificacao.html',
        controller: 'notifyCtrl',
        requireLogin : false
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
