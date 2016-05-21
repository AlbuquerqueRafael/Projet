/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").controller("notifyCtrl", function($scope){

    $scope.notificacoes = ["Fulano Aceitou sua Carona", "Ciclano aceitou sua carona",
      "Nossa, assim você me mata", "Batatinha quando nasce"

    ];

    $scope.descartaNotify = function(index){
        $scope.notificacoes.splice(index, 1);

    }

});
