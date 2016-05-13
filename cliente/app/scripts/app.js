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
    'ngFileUpload',
    'pascalprecht.translate'

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
        requireLogin : true

      })
      .when('/horario', {
        templateUrl: 'views/usuario/horario.html',
        controller: 'horarioCtrl',
        requireLogin : true
      })
      .when('/carona', {
        templateUrl: 'views/usuario/carona.html',
        controller: 'caronaCtrl',

        requireLogin : true
      })
      .when('/buscar', {
        templateUrl: 'views/usuario/busca.html',
        controller: 'buscaCtrl',
        requireLogin : true
      })
      .when('/notificacao', {
        templateUrl: 'views/usuario/notificacao.html',
        controller: 'notifyCtrl',
        requireLogin : true
      })
      .when('/solicitacao', {
        templateUrl: 'views/usuario/solicitacao.html',
        controller: 'notifyCtrl',
        requireLogin : true
      })
      .otherwise({
        redirectTo: '/'
      });

  });

angular.module("clienteApp").config(function($translateProvider) {
  $translateProvider.translations('en', {
      'label.question.movie': "What is your favorite movie?",      
      'cadastro.title': "Sign up!",
      'cadastro.nome': "Name",
      'cadastro.email': "Email",
      'cadastro.email.check': "You must enter a valid email address. Example caroname@caroname.com",
      'cadastro.senha': "Password",
      'cadastro.senha.check': "At least 8 characters",
      'cadastro.matricula': "Number of enrollment",
      'cadastro.matricula.check': "Type the 9 numbers of enrollment in UFCG",
      'cadastro.rua': "Address",
      'cadastro.telefone': "Phone number",
      'cadastro.telefone.ddd': "Phone number without DDD",
      'cadastro.telefone.check': "Type your phone number", 
      'label.title': "Vote in Movie?"
  });
  $translateProvider.translations('pt', {      
      'cadastro.title': "Cadastro!",
      'label.question.movie': "Qual filme você gosta mais?",
      'cadastro.nome': "Nome",
      'cadastro.email': "Email",
      'cadastro.email.check': "Preencha com um email válido. Exemplo caroname@caroname.com",
      'cadastro.senha': "Senha",
      'cadastro.senha.check': "Pelo menos 8 caracteres",
      'cadastro.matricula': "Nº Matricula",
      'cadastro.matricula.check': "Digite os 9 números da sua matrícula da UFCG",
      'cadastro.rua': "Rua",
      'cadastro.telefone': "Telefone",
      'cadastro.telefone.ddd': "Telefone sem DDD",
      'cadastro.telefone.check': "Digite os números do seu telefone",
      'label.title': "Vote no Filme"
  });

  $translateProvider.preferredLanguage('pt'); //temos que dizer qual é a default.
});

angular.module('clienteApp').controller('Ctrl', ['$translate', '$scope', function ($translate, $scope) {
 
  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
  };
 
}]);


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
