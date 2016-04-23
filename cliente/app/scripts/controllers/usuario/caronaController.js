/**
 * Created by rafael on 17/04/16.
 */

angular.module("clienteApp")
    .controller("caronaCtrl", function($scope, $location, mainService, caronaService, horarioService){

        $scope.bairros = caronaService.getBairrosArray();
        $scope.horarios = caronaService.getAulaArray();
        $scope.diaSemanas = caronaService.getDiaSemanasArray();
        $scope.NumVagas = caronaService.getNumVagasArray();
        $scope.tabs = true;
        $scope.routes = [];
        $scope.horario = {};
        $scope.horario.DiaDaSemana =  $scope.diaSemanas[0];
        $scope.horario.aula =  $scope.horarios[0];
        $scope.vagas = $scope.NumVagas[2];
        $scope.rota = $scope.bairros[0];
        $scope.opcaoCarona = "0";
      $scope.usuario = mainService.getUserAtual();
        $scope.bairro = $scope.usuario.endereco.bairro;
        $scope.rua = $scope.usuario.endereco.rua;



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

        var filtraRotaPorValor = function(routes){
            var rotas = [];
            for(var i = 0; i < $scope.routes.length; i++){
                rotas.push( $scope.routes[i].value);
            }


            return rotas;
        }


        $scope.salvarCarona = function(){
            var carona = {};
            carona.horario = {}
            carona.horario = {}
            carona.horario.aula = $scope.horario.aula.value;
            carona.horario.dia = $scope.horario.DiaDaSemana.Id;
            carona.tipo = parseInt($scope.opcaoCarona);
            carona.vagas = $scope.vagas.value;
            carona.endereco = {}
            carona.endereco.rua = $scope.rua;
            carona.endereco.bairro = $scope.bairro.value;
            carona.rota = filtraRotaPorValor($scope.routes);

            horarioService.salvarHorario(carona).success(function(info){
               $location.path("/horario");
            }).error(function(erro){
              alert(erro);
            });


        }

        $scope.cancelar = function(){
          $location.path('/horario');
        }










    });
