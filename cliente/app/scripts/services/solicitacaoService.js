/**
 * Created by rafael on 19/04/16.
 */
'use strict';

angular.module("clienteApp").factory("solicitacaoService", function($http){

  var solicitacaoService = {};

  solicitacaoService.getPedidosCaronas = function(id){
    return $http.get('/app/solicitacoesCarona/' + id);
  };

  solicitacaoService.aceitarCarona = function(idSolicitacao){
    return $http.post('/app/aceitarCarona/' + idSolicitacao);
  };

  solicitacaoService.rejeitarCarona = function(idSolicitacao){
    return $http.post('/app/rejeitarCarona/' + idSolicitacao);
  };

  solicitacaoService.getCaronasRejeitadas = function(){
    return $http.get('/app/caronasRejeitadas');
  };

  solicitacaoService.solicitarCarona = function(IdCaronaSolicitada){
    return $http.post('/app/solicitarCarona/' + IdCaronaSolicitada);
  };

  return solicitacaoService;



});
