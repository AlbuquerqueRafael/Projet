/**
 * Created by rafael on 20/03/16.
 */
'use strict';

angular.module("clienteApp").factory("mainService", function($http, $cookieStore, $location){

  var service = {};

  service.logar = function(dados){
    return $http.post("/app/logar", dados);
  };

  service.cadastrar = function(usuario){
    return $http.post("/app/cadastrar", usuario);

  };

  service.logout = function(){
    $cookieStore.remove('login');
    return $http.post("/app/logout");
  };

  service.autenticar = function(usuario){
    $cookieStore.put('login', usuario);
  };

  service.getUserAtual = function(){
    return $cookieStore.get('login');
  };

  service.logout = function(){
    $cookieStore.remove('login');
  };

  service.trocar = function(texto){
    if(texto === "/views/index"){
      service.logout();
    }
    $location.path(texto);
  };



  return service;

});
