/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").controller("notifyCtrl", function($scope, notifyService){

  var id = 1;
  $scope.solicitacoes = [];
  $scope.quantidadeElementosPagina = $scope.solicitacoes.length;

  $scope.buscarSolicitacoes = function(pagina){
    if('proxima' === pagina){
      id += 1;
    }else if(id > 1){
      id -= 1;
    }

    notifyService.getPedidosCaronas(id).success(function(info){
      $scope.solicitacoes = info;
      $scope.quantidadeElementosPagina = info.length;
    }).error(function(erro){
      console.log(erro);
    });
  }

  $scope.buscarSolicitacoes(id);

  $scope.aceitarSolicitacao = function(solicitacao){

    notifyService.aceitarCarona(solicitacao).success(function(info){
      $scope.telefone = info;
      id = 1;
      $scope.buscarSolicitacoes(id);
    }).error(function(erro){
      console.log(erro);
    });

  };

});
