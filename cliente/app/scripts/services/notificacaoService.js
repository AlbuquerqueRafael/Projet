/**
 * Created by rafael on 21/05/16.
 */


angular.module("clienteApp").factory("notifyService", function($http){

  var notifyService = {};

  notifyService.getNotificacoes = function(){
     return $http.get("/app/notificacoes/");
  };

  notifyService.removeNotificacoes = function(index){
    return $http.delete("/app/removenotificacoes/" + index);
  };


  return notifyService;
});
