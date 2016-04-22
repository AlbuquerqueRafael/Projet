/**
 * Created by rafael on 19/04/16.
 */
'use strict';

angular.module("clienteApp").factory("notifyService", function($http){

  var notifyService = {};

  notifyService.getPedidosCaronas = function(id){
    return $http.get('/app/solicitacoesCarona/' + id);
  };

  notifyService.aceitarCarona = function(solicitacao){
    return $http.post('/app/aceitarCarona', solicitacao);
  };

  notifyService.rejeitarCarona = function(solicitacao){
    return $http.post('/app/rejeitarCarona', solicitacao);
  };

  notifyService.getCaronasRejeitadas = function(){
    return $http.get('/app/caronasRejeitadas');
  };


  return notifyService;



});
