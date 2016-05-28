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
        requireLogin : false
      })
      .when('/solicitacao', {
        templateUrl: 'views/usuario/solicitacao.html',
        controller: 'solicitacaoCtrl',
        requireLogin : false
      })
      .otherwise({
        redirectTo: '/'
      });

  });

angular.module("clienteApp").config(function($translateProvider) {
  $translateProvider.translations('en', {
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

      'main.email': "Email",
      'main.email.type': "Type your email",
      'main.senha': "Password",
      'main.senha.type': "Type your password",
      'main.cadastro': "Sign up",

      'horario.bemvindo': "Welcome",
      'horario.saudacao': "Here you will find informations and can ask a ride or create a ride",
      'horario.oferecer': "Offer a ride",
      'horario.buscar': "Search a ride",
      'horario.motorista': "Driver",
      'horario.passageiro': "Passenger",

      'nome': "Name",
      'diaDaSemana': "Day",
      'diaDaSemanaComplete': "Day of the week",
      'aula': "Class",
      'tipoDeCarona': "Kind of ride",
      'vagas': "Vacancies",
      'bairro': "Neighborhood",
      'rua': "Address",
      'endereco': "Address",
      'telefone': "Phone number",
      'tipoDeCarona.ida': "One-way trip",
      'tipoDeCarona.volta': "Back trip",
      'rotas': "Routes",
      'email': "Email",
      'adicionar': "Add",
      'remover': "Remove",
      'salvar': "Save",
      'cancelar': "Cancel",
      'buscar': "Search",
      'numero': "Number: ",
      'dia.aula': "Day/Class",
      'proxPag': "Next page",
      'antPag': "Previous page",

      'bemvindoSolicitacoes': "Welcome to Requests page!",
      'saudacaoSolicitacoes': "Here you will find the requests received of the pending rides",
      'aceitar': "Accept",
      'rejeitar': "Reject",
      'pedidoAceito': "You accepted the request!",
      'numeroPassageiro': "The phone number of the passenger is: ",

      'notificacoes': "Notifications",
      'bemvindoNotificacoes': "Welcome to Notifications page!",
      'saudacaoNotificacoes': "Here you will find the notifications of accepted or rejected requests.",

      'label.title': "Vote in Movie?"
  });
  $translateProvider.translations('pt', {
      'cadastro.title': "Cadastro!",
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

      'main.email': "Email",
      'main.email.type': "Digite seu email",
      'main.senha': "Senha",
      'main.senha.type': "Digite a senha",
      'main.cadastro': "Cadastrar",

      'horario.bemvindo': "Bem vindo",
      'horario.saudacao': "Aqui você encontra informações, bem como pode pedir ou criar uma carona",
      'horario.oferecer': "Oferecer Carona",
      'horario.buscar': "Buscar carona",
      'horario.motorista': "Motorista",
      'horario.passageiro': "Passageiro",

      'nome': "Nome",
      'diaDaSemana': "Dia da Semana",
      'diaDaSemanaComplete': "Dia da Semana",
      'aula': "Aula",
      'tipoDeCarona': "Tipo",
      'vagas': "Vagas",
      'bairro': "bairro",
      'rua': "Rua",
      'endereco': "Endereço",
      'telefone': "Telefone",
      'tipoDeCarona.ida': "Ida",
      'tipoDeCarona.volta': "Volta",
      'rotas': "Rotas",
      'email': "E-mail",
      'adicionar': "Adicionar",
      'remover': "Remover",
      'salvar': "Salvar",
      'cancelar': "Cancelar",
      'buscar': "Buscar",
      'numero': "Tel:  ",
      'dia.aula': "Dia/Aula",
      'proxPag': "Próxima página",
      'antPag': "Página anterior",

      'bemvindoSolicitacoes': "Bem-vindo a página de Solicitações!",
      'saudacaoSolicitacoes': "Aqui você encontra os pedidos recebidos de caronas que estão pendentes.",
      'aceitar': "Aceitar",
      'rejeitar': "Rejeitar",
      'pedidoAceito': "Você aceitou o pedido!",
      'numeroPassageiro': "O telefone do passageiro é : ",

      'notificacoes': "Notificações",
      'bemvindoNotificacoes': "Bem-vindo à página de Notificações!",
      'saudacaoNotificacoes': "Aqui você encontrará os pedidos aceitos e rejeitados.",


      'label.title': "Vote no Filme"
  });

  $translateProvider.preferredLanguage('pt'); //temos que dizer qual é a default.
});

/*angular.module('clienteApp').controller('Ctrl', ['$translate', '$scope', function ($translate, $scope) {

  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
  };

}]);*/


angular.module("clienteApp").run(function($rootScope, $location, menuService, mainService, $http){
  $rootScope.$on('$routeChangeStart', function(event, next, current) {

    //Logout -> Deve haver soluções melhores
    if ($location.path() === "/sair") {
      if(menuService.getMenu() === "Usuario"){
        menuService.setMenu("inicio");
      }else{
        menuService.setMenu("MenuInicialEmIngles");
      }

      mainService.logout();
      $location.path("/");
    }


    //Algoritmo para não deixar algum usario entrar sem estar logado
    if (next.$$route === undefined || (next.$$route.requireLogin && "Usuario" !== menuService.getMenu() && "UsuarioEmIngles" !== menuService.getMenu()) )  {
       $location.path("/inicio");
    }

    if (mainService.getUserAtual() !== undefined) {
       $http.defaults.headers.common['X-AUTH-TOKEN'] = mainService.getUserAtual().authToken;
     }

    //$http.interceptors.push('ApiInterceptorService');

  });
});
