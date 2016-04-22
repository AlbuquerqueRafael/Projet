/**
 * Created by rafael on 05/04/16.
 */

'use strict';

angular.module("clienteApp").controller("buscaCtrl", function($scope, caronaService, mainService, $window) {

  $scope.horarios = caronaService.getAulaArray();
  $scope.diaSemanas = caronaService.getDiaSemanasArray();
  $scope.bairros = caronaService.getBairrosArray();

  $scope.usuario = mainService.getUserAtual();
  $scope.tabs = true;
  $scope.infoCaronas = [];
  var id = 1;
  $scope.quantidadeElementosPagina = $scope.infoCaronas.length;
  $scope.error = false;
  $scope.horario = {};
  $scope.horario.aula =  $scope.horarios[0];
  $scope.horario.DiaDaSemana =  $scope.diaSemanas[0];
  $scope.opcaoCarona = "0";


  $scope.inicia = function(){
    for(var i = 0; i < $scope.bairros.length; i++){
      if($scope.bairros[i].value === $scope.usuario.endereco.bairro){
        $scope.bairro = $scope.bairros[i];
        $scope.rua = $scope.usuario.endereco.rua;
        break;
      }
    }
  };

  $scope.inicia();


  $scope.buscar = function(pagina){
    if('proxima' === pagina){
      id += 1;
    }else if(id > 1){
      id -= 1;
    }
    var carona = {};
    var horarioDaCarona = {};
    var enderecoCarona = {};

    horarioDaCarona.aula = $scope.horario.aula.value;
    horarioDaCarona.dia = $scope.horario.DiaDaSemana.Id;

    enderecoCarona.tipo = parseInt($scope.opcaoCarona);

    enderecoCarona.rua = $scope.rua;
    enderecoCarona.bairro = $scope.bairro.value;

    carona.horario = horarioDaCarona;
    carona.endereco = enderecoCarona;
    carona.tipo = $scope.opcaoCarona;

    caronaService.buscarCarona(carona, id).success(function(info){
      $scope.infoCaronas = info;
      $scope.error = false;
      $scope.quantidadeElementosPagina =  $scope.infoCaronas.length;
    }).error(function(erro){
      $scope.error = true;
      $scope.errorMessage = erro;
    });

  };


  $scope.solicitaCarona = function(carona){
    var caronaSolicitada = {};
    var horario = {};
    var horarioSolicitacao = $scope.horario;

    console.log(carona);
    caronaSolicitada.motorista = carona.motorista;

    horario.aula = horarioSolicitacao.aula.value;
    horario.dia = horarioSolicitacao.DiaDaSemana.Id;

    caronaSolicitada.horario = horario;
    caronaSolicitada.tipo = $scope.opcaoCarona;
    console.log("teste");
    console.log(caronaSolicitada);

    caronaService.solicitarCarona(caronaSolicitada).success(function(info){
        $scope.infoCaronas = $scope.infoCaronas.filter(function(e1){
          return e1.motorista.nome !== carona.motorista.nome;
        });
      $window.alert(info);

    }).error(function(erro){
        console.log(erro);
    });


  };

});