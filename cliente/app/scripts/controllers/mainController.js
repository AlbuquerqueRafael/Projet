'use strict';

/**
 * @ngdoc function
 * @name clienteApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clienteApp
 */
angular.module('clienteApp')
  .controller('MainCtrl', function ($scope, $http, $location, mainService, menuService) {


    $scope.error= false;
    $scope.users = function(){
      $http.get("/app/getUsers ").success(function(info){
        console.log(info);
      }).error(function(error){
        console.log(error);

      });
    }

    $scope.minhasOpcoes = "motorista";

    $scope.logar = function(usuario) {
      mainService.logar(usuario).success(function(info){
        usuario = info;
        mainService.credenciar(usuario);

        if($scope.opcao.value === "Motorista"){
          menuService.setMenu("Motorista");
        }else{
          menuService.setMenu("Passageiro");
        }
        $location.path("/horario");
      }).error(function(error){
        $scope.error = true;
        $scope.errorMessage = error;

      });
    }

    $scope.cadastrar = function(usuario){
      if($scope.opcao.value === "Motorista"){
        usuario.vagas = vagas;
      }
      mainService.cadastrar(usuario, $scope.opcao.value).success(function(info){
        $location.path("/");
        console.log(info);
      }).error(function(error){
        $scope.error = true;
        $scope.errorMessage = error;

      });
    }



    $scope.trocar = function(){
      $location.path('/cadastro');
    }



    $scope.options = [{value: 'Motorista'}, {value: 'Passageiro'}]
    $scope.opcao = $scope.options[0];
  });
