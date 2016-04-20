/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").controller("notifyCtrl", function($scope, notifyService){

  var id = 1;
  $scope.solicitacoes = [];

  var inicia = notifyService.getPedidosCaronas(id).success(function(info){
      $scope.solicitacoes = info;
      console.log(info);
  }).error(function(erro){
      console.log(erro);
  });

  inicia;

});
