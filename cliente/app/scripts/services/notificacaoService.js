/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").factory("notifyService", function($http){

  var notifyService = {};

  notifyService.getPedidosCaronas = function(id){
    return $http.get('/app/pedidosCaronas/' + id);
  }


  return notifyService;



});
