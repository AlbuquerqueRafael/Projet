/**
 * Created by rafael on 27/03/16.
 */
angular.module("clienteApp").controller("horarioCtrl", function($scope, horarioService, mainService, $filter, $location) {

  $scope.bairros = [{value:"Malvinas", add: false},
                    {value:"Centro", add: false},
                    {value:"Prata", add: false},
                    {value:"Catolé", add: false},
                    {value:"Alto Branco", add: false}
  ]

  $scope.error = false;
  $scope.usuario = mainService.getUserAtual();
  $scope.bairro = $scope.bairros[1];
  $scope.data = $filter('date')(new Date(), 'dd/MM/yyyy');
  $scope.horarios = [];
  $scope.routes = [];
  $scope.opcaoCarona = "Ida";
  $scope.hora = new Date();

  $scope.hstep = 1;
  $scope.mstep = 10;

  $scope.ismeridian = true;

  $scope.toggleMode = function() {
    $scope.ismeridian = ! $scope.ismeridian;
  };

  $scope.inicio = horarioService.getHorarios($scope.usuario).success(function(caronas){
    console.log($scope.usuario);
    $scope.horarios = caronas;
  }).error(function(error){
    console.log(error);

  });


  $scope.adicionarRotas = function(bairro){
    if(!bairro.add){
      bairro.add = true;
      $scope.routes.push(bairro);
    }

  }

  $scope.removerRota = function(){
    var routes = [];
    for(var i = 0; i < $scope.routes.length; i++){
      if(!$scope.routes[i].selected){
        routes.push($scope.routes[i]);
      }else{
        $scope.routes[i].add = false;
      }
    }

    $scope.routes = routes;

  }

    $scope.trocarRota = function(rota){
      console.log(rota);
      if(rota === "OferecerCarona"){
         $location.path('/carona')
      }else{
        $location.path('/buscar')
      }
    }

    $scope.salvarCarona = function(){
        var separaData = $scope.data.split("/");
        var data = new Date(separaData[2], separaData[1] - 1, separaData[0]);

        if($scope.hora !== null) {
          var carona = {};
          carona.horario = {};
          carona.horario.data = data;
          carona.horario.hora = $scope.hora;
          carona.horario.tipo = $scope.opcaoCarona;
          $scope.usuario.caronas = []
          $scope.usuario.caronas.push(carona);
          $scope.error = false;
          console.log($scope.usuario);
          horarioService.salvarHorario($scope.usuario).success(function (info) {
            console.log("dd");
          }).error(function(error){
            console.log(error);

          });
        }else{
          $scope.error = true;
          $scope.errorMessage = "Hora inválida";
        }

    }






});
