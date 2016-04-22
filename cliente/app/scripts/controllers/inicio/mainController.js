'use strict';

/**
 * @ngdoc function
 * @name clienteApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clienteApp
 */
angular.module('clienteApp')
  .controller('MainCtrl', function ($scope, $http, $location, mainService, menuService, caronaService) {

    $scope.bairros = caronaService.getBairrosArray();
    $scope.error= false;
    $scope.users = function(){
      $http.get("/app/getUsers ").success(function(info){
        console.log("Sucesso!");
      }).error(function(error){
        console.log(error);

      });
    }

    $scope.minhasOpcoes = "motorista";

    $scope.logar = function(usuario) {
      mainService.logar(usuario).success(function(info){
        mainService.autenticar(info);
        menuService.setMenu("Usuario");
        $location.path("/horario");

      }).error(function(error){
        $scope.error = true;
        $scope.errorMessage = error;

      });
    }

    $scope.cadastrar = function(usuario){
      if(usuario.endereco.bairro.value === undefined){
        $scope.error = true;
        $scope.errorMessage = "Selecione um bairro";
      }else {
        usuario.endereco.bairro = usuario.endereco.bairro.value;

        mainService.cadastrar(usuario).success(function (info) {
          $location.path("/");
        }).error(function (error) {
          $scope.error = true;
          $scope.errorMessage = error;

        });
      }
    }

    $scope.trocar = function(){
      $location.path('/cadastro');
    }


  });
