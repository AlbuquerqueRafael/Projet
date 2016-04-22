
/**
 * Created by rafael on 27/03/16.
 */
'use strict';

angular.module("clienteApp").factory("horarioService", function($http){
    var horarioService = {};

    horarioService.getHorariosMotorista = function(){
         return $http.get("/app/caronasMotorista");
    };

    horarioService.salvarHorario = function(carona){
          return $http.post("/app/salvarCarona", carona);
    };

    horarioService.getHorariosPassageiro = function(){
         return $http.get("/app/caronasPassageiro");
    };

    return horarioService;
});
