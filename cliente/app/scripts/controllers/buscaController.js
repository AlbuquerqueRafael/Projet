/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").controller("buscaCtrl", function($scope, caronaService, mainService) {

  $scope.horarios = caronaService.getAulaArray();
  $scope.diaSemanas = caronaService.getDiaSemanasArray();
  $scope.bairros = caronaService.getBairrosArray();

  $scope.users = []; //declare an empty array
  $scope.pageno = 1; // initialize page no to 1
  $scope.total_count = 0;
  $scope.itemsPorP = 6;
  $scope.usuario = mainService.getUserAtual();
  $scope.tabs = true;
  $scope.motoristas = [];
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
  }

  $scope.inicia();


  $scope.buscar = function(){
    var carona = {};
    carona.horario = {}
    carona.horario.aula = $scope.horario.aula.value;
    carona.horario.dia = $scope.horario.DiaDaSemana.Id;
    carona.tipo = parseInt($scope.opcaoCarona);
    carona.endereco = {}
    carona.endereco.rua = $scope.rua;
    carona.endereco.bairro = $scope.bairro.value;

    caronaService.buscarCarona(carona, pageN).success(function(info){
      $scope.motoristas = info;
      console.log($scope.motoristas);
    }).error(function(erro){
      $scope.error = true;
      $scope.errorMessage = erro;
      console.log("errou");
      console.log(erro);
    });


  }

});
