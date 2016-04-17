/**
 * Created by rafael on 27/03/16.
 */
angular.module("clienteApp").factory("horarioService", function($http){
    var horarioService = {};

    horarioService.getHorarios = function(usuario){
         return $http.post("/app/caronas", usuario);
    }

    horarioService.salvarHorario = function(usuario){
          return $http.post("/app/salvarCarona", usuario);
    }

    horarioService.getAulaArray = function(){
        var aula =[{value: "8-10"},
            {value: "10-12"},
            {value: "14-16"},
            {value: "16-18"},
            {value:"18-20"}];

        return aula;
    }

    horarioService.getNumVagasArray = function() {
        var vagas =  [{value: 1},
            {value: 2},
            {value: 3},
            {value: 4},
            {value: 5}];
    }

    horarioService.getDiaSemanasArray = function() {
        var diaSemana = [{Id: 0, value: "Segunda"},
        {Id: 1, value: "Terça"},
        {Id: 2, value: "Quarta"},
        {Id: 3, value:"Quinta"},
        {Id: 4, value:"Sexta"}];

        return diaSemana;
    }

  return horarioService;
});
