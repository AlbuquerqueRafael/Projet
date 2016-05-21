/**
 * Created by rafael on 20/05/16.
 */


angular.module("clienteApp").controller("solicitacaoCtrl", function($scope){


  var id = 1;
  $scope.tabs = 1;
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

  $scope.rejeitarPedido = function(solicitacao){
    notifyService.rejeitarCarona(solicitacao).success(function(info){
      $scope.telefone = info;
      id = 1;
      $scope.buscarSolicitacoes(id);
    }).error(function(erro){
      console.log(erro);
    });
  };

  $scope.solicitacoesRejeitadas = function(){
    notifyService.getCaronasRejeitadas().success(function(info){
      $scope.tabs = 2;
      console.log(info);
      $scope.mensagens = info;
    }).error(function(erro){
      console.log(erro);
    });
  }

});
