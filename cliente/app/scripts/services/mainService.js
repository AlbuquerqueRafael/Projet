/**
 * Created by rafael on 20/03/16.
 */

angular.module("clienteApp").factory("mainService", function($http, $cookieStore, $location){

  var service = {}

  service.logar = function(dados){
    return $http.post("/app/logar", dados);
  }

  service.cadastrar = function(usuario, tipo){
    console.log(tipo)
    if(tipo === "Motorista"){
      return $http.post("/app/cadastrarMotorista", usuario);
    }else{
      return $http.post("/app/cadastrarPassageiro", usuario);
    }

  }


  service.credenciar = function(login){
    if ($cookieStore.get('login') !== undefined){
      $cookieStore.remove('login');
    }

    $cookieStore.put('login', login);

    /*   $http.defaults.headers.common['Authorization'] = 'Basic ' + professor.senha; // jshint ignore:line
     $cookieStore.put('globals', $rootScope.globals);*/
  }

  service.logout = function(){
    $cookieStore.remove('login');
  }

  service.getUserAtual = function(){
    return $cookieStore.get('login');
  }

  service.trocar = function(texto){
    if(texto === "/views/index"){
      service.logout();
    }
    $location.path(texto);
  }

  return service;


  /* service.login = function(usuario){
       return $resource('/usuario:id',{id: "@id"});
   }*/

});
