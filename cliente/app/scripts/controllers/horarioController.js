/**
 * Created by rafael on 27/03/16.
 */
angular.module("clienteApp").controller("horarioCtrl", function($scope, horarioService, mainService, $filter, $location) {

  //noinspection JSDuplicatedDeclaration

  $scope.tab = 1;

  console.log($scope.tab);
  $scope.error = false;

  $scope.inicio = horarioService.getHorarios().success(function(caronas){
    console.log(caronas);
    $scope.caronas = caronas;
  }).error(function(error){
    console.log(error);

  });


    $scope.trocarRota = function(rota){
      console.log(rota);
      if(rota === "OferecerCarona"){
         $location.path('/carona')
      }else if(rota=== "gg"){
        $location.path('/buscar')
      }else{
          $location.path('/horario')
      }
    }



});
