/**
 * Created by rafael on 17/04/16.
 */
'use strict';

angular.module("clienteApp").factory("caronaService", function($http){

    var caronaService = {};

    caronaService.buscarCarona = function(carona, numPagina){
      if(numPagina === undefined){
        numPagina = 1;
      }
      return $http.post('/app/buscarCaronas/' + numPagina, carona);
    };

    caronaService.getAulaArray = function(){
        var aula =[{value: "8-10"},
            {value: "10-12"},
            {value: "14-16"},
            {value: "16-18"},
            {value:"18-20"}];

        return aula;
    };

    caronaService.getNumVagasArray = function() {
        var vagas =  [{value: 1},
            {value: 2},
            {value: 3},
            {value: 4},
            {value: 5}];

        return vagas;
    };

    caronaService.getDiaSemanasArray = function() {
        var diaSemana = [{Id: 0, value: "Segunda"},
            {Id: 1, value: "Terça"},
            {Id: 2, value: "Quarta"},
            {Id: 3, value:"Quinta"},
            {Id: 4, value:"Sexta"}];

        return diaSemana;
    };

    caronaService.getBairrosArray = function(){
        var bairros = [{Id: 1, value:"Alto Branco", add: false},
            {Id: 2, value:"Bairro das Cidades", add: false},
            {Id: 3, value:"Bela Vista", add: false},
            {Id: 4, value:"Bodocongó", add: false},
            {Id: 5, value:"Castelo Branco", add: false},
            {Id: 6, value:"Catolé", add: false},
            {Id: 7, value:"Centro", add: false},
            {Id: 8, value:"Cruzeiro", add: false},
            {Id: 9, value:"Dinamérica", add: false},
            {Id: 10, value:"Distrito Industrial", add: false},
            {Id: 11, value:"Jardim Paulistano", add: false},
            {Id: 12, value:"José Pinheiro", add: false},
            {Id: 13, value:"Liberdade", add: false},
            {Id: 14, value:"Malvinas", add: false},
            {Id: 15, value:"Prata", add: false},
            {Id: 16, value:"Pedregal", add: false},
            {Id: 17, value:"Presidente Médici", add: false},
            {Id: 18, value:"Ramadinha", add: false},
            {Id: 19, value:"Santa Rosa", add: false},
            {Id: 20, value:"São José", add: false},
        ];

        return bairros;
    };

    return caronaService;

});
