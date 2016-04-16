/**
 * Created by rafael on 27/03/16.
 */
angular.module("clienteApp").controller("horarioCtrl", function($scope, horarioService, mainService, $filter, $location) {

  $scope.bairros = [{Id: 1, value:"Malvinas", add: false},
                    {Id: 2, value:"Centro", add: false},
                    {Id: 3, value:"Prata", add: false},
                    {Id: 4, value:"Catolé", add: false},
                    {Id: 5, value:"Alto Branco", add: false}
  ]

  $scope.NumVagas = [{value: 1},
                  {value: 2},
                  {value: 3},
                  {value: 4},
                  {value: 5}];

  $scope.diaSemanas = [ {Id: 0, value: "Segunda"},
                        {Id: 1, value: "Terça"},
                        {Id: 2, value: "Quarta"},
                        {Id: 3, value:"Quinta"},
                        {Id: 4, value:"Sexta"}];

  $scope.horarios = [{value: "8-10"},
                    {value: "10-12"},
                    {value: "14-16"},
                    {value: "16-18"},
                    {value:"18-20"}];


  $scope.horario = {};
  $scope.horario.DiaDaSemana =  $scope.diaSemanas[0];
  $scope.horario.aula =  $scope.horarios[0];
  $scope.vagas = $scope.NumVagas[0];


  $scope.error = false;
  $scope.bairro = $scope.bairros[1];
  $scope.data = $filter('date')(new Date(), 'dd/MM/yyyy');
  $scope.routes = [];
  $scope.opcaoCarona = "0";
  $scope.hora = new Date();

  $scope.hstep = 1;
  $scope.mstep = 10;

  $scope.ismeridian = true;

  $scope.toggleMode = function() {
    $scope.ismeridian = ! $scope.ismeridian;
  };

  $scope.inicio = horarioService.getHorarios().success(function(caronas){
    console.log(caronas);
    $scope.caronas = caronas;
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
          console.log("gg")
          var carona = {};
          carona.horario = {}
          carona.horario = {}
          carona.horario.aula = $scope.horario.aula.value;
          carona.horario.dia = $scope.horario.DiaDaSemana.Id;
          carona.horario.tipo = parseInt($scope.opcaoCarona);
          carona.vagas = $scope.vagas.value;


          horarioService.salvarHorario(carona).success(function(info){
            console.log(info);
          }).error(function(erro){
            console.log(erro);
          });


    }






});
