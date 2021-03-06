'use strict';


angular.module('clienteApp')
  .controller('MainCtrl', function ($scope, $http, $location, mainService, menuService, caronaService) {

    $scope.bairros = caronaService.getBairrosArray();
    $scope.error= false;



    $scope.logar = function(usuario) {
      mainService.logar(usuario).success(function(info){
        console.log(info);
        mainService.autenticar(info);
        if(menuService.getMenu() === "inicio"){
          menuService.setMenu("Usuario");
        }else{
          menuService.setMenu("UsuarioEmIngles");
        };

        $location.path("/horario");

      }).error(function(error){
        $scope.error = true;
        $scope.errorMessage = error;

      });
    };

    $scope.cadastrar = function(usuario) {
      console.log(usuario);
      if($scope.usuario.endereco.bairro !== undefined) {
        usuario.endereco.bairro = $scope.usuario.endereco.bairro.value;
      }
      mainService.cadastrar(usuario).success(function (info) {
        $location.path("/");
      }).error(function (error) {

        $scope.error = true;
        $scope.errorMessage = error;

      });
    };


    $scope.trocar = function(){
      $location.path('/cadastro');
    }


  });
