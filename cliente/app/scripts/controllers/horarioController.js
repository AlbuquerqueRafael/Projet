/**
 * Created by rafael on 27/03/16.
 */

'use strict';

angular.module("clienteApp").controller("horarioCtrl", function($scope, horarioService, mainService, $filter, $location) {
    $scope.tab = 1;
    $scope.error = false;

    $scope.horariosMotoristas = function() {
      horarioService.getHorariosMotorista().success(function (caronas) {
        $scope.tab = 1;
        $scope.caronas = caronas;
      }).error(function (error) {
        console.log(error);
      });
    };

    $scope.horariosMotoristas();

    $scope.horariosPassageiro = function() {
      horarioService.getHorariosPassageiro().success(function (caronas) {
        $scope.tab = 2;
        $scope.caronas = caronas;
      }).error(function (error) {
        console.log(error);
      });
    };


    $scope.trocarRota = function(rota){
      if(rota === "OferecerCarona"){
         $location.path('/carona');
      }else if(rota=== "gg"){
        $location.path('/buscar');
      }else{
          $location.path('/horario');
      }
    };



});
