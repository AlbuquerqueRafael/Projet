/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").factory("notifyService", function($http){

  var notifyService = {};

  notifyService.getPedidosCaronas = function(id){
    return $http.get('/app/solicitacoesCarona/' + id);
  };

  notifyService.aceitarCarona = function(solicitacao){
    return $http.post('/app/aceitarCarona', solicitacao);
  }


  return notifyService;



});
