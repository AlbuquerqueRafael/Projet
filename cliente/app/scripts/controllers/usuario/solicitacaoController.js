/**
 * Created by rafael on 20/05/16.
 */


angular.module("clienteApp").controller("solicitacaoCtrl", function($scope, solicitacaoService){


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

    solicitacaoService.getPedidosCaronas(id).success(function(info) {
      if (info !== "Sem solicitacoes"){
          $scope.solicitacoes = info;
          $scope.quantidadeElementosPagina = info.length;
      };
      console.log("caracas");
    }).error(function(erro){
      $scope.solicitacoes .splice(0, 1);
      console.log(erro);
    });
  }

  $scope.buscarSolicitacoes(id);

  $scope.aceitarSolicitacao = function(solicitacao){
    var idSolicitacao = solicitacao.id;
    solicitacaoService.aceitarCarona(idSolicitacao).success(function(info){
      $scope.telefone = info;
      id = 1;
      $scope.buscarSolicitacoes(id);
    }).error(function(erro){
      console.log(erro);
    });
  };

  $scope.rejeitarPedido = function(solicitacao){
    var idSolicitacao = solicitacao.id
    solicitacaoService.rejeitarCarona(idSolicitacao).success(function(info){
      $scope.telefone = info;
      id = 1;
      $scope.buscarSolicitacoes(id);
    }).error(function(erro){
      console.log(erro);
    });
  };

  $scope.solicitacoesRejeitadas = function(){
    solicitacaoService.getCaronasRejeitadas().success(function(info){
      $scope.tabs = 2;
      console.log(info);
      $scope.mensagens = info;
    }).error(function(erro){
      console.log(erro);
    });
  }

});
