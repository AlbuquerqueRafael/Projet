/**
 * Created by rafael on 19/04/16.
 */

angular.module("clienteApp").controller("notifyCtrl", function($scope, notifyService){

    var inicio = notifyService.getNotificacoes().success(function(data){
        $scope.notificacoes = data;
    });;
    console.log($scope.notificacoes);
    $scope.descartaNotify = function(index){
        notifyService.removeNotificacoes(index).success(function(data){
          $scope.notificacoes.splice(index, 1);
        }).error(function(erro){
          console.log(erro);
        });

    }

});
