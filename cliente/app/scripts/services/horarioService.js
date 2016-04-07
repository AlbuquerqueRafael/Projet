/**
 * Created by rafael on 27/03/16.
 */
angular.module("clienteApp").factory("horarioService", function($http){
    var horarioService = {};

    horarioService.getHorarios = function(usuario){
         return $http.post("/app/horario", usuario);
    }

    horarioService.salvarHorario = function(usuario){
          return $http.post("/app/salvarHorario", usuario);
    }


  return horarioService;
});
