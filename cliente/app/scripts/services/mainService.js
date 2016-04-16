/**
 * Created by rafael on 20/03/16.
 */

angular.module("clienteApp").factory("mainService", function($http, $cookieStore, $location){

  var service = {}

  service.logar = function(dados){
    return $http.post("/app/logar", dados);
  }

  service.cadastrar = function(usuario){
    return $http.post("/app/cadastrar", usuario);

  }

  service.logout = function(){
    $cookieStore.remove('login');
    return $http.post("/app/logout");
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

});
