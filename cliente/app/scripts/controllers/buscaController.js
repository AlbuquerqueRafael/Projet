/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").controller("buscaCtrl", function($scope, horarioService, mainService) {
  $scope.filteredTodos = []
  $scope.currentPage = 1
  $scope.numPerPage = 10
  $scope.maxSize = 5;
  $scope.usuario = mainService.getUserAtual();
  $scope.horarios = [];

  $scope.makeTodos = function() {
    $scope.todos = [];
    for (i=1;i<=1000;i++) {
      $scope.todos.push({ text:'todo '+i, done:false});
    }
  };
  $scope.makeTodos();

  $scope.inicio = horarioService.getHorarios($scope.usuario).success(function(horarios){
    $scope.horarios = horarios;
    console.log($scope.horarios);
  });

  $scope.opcaoHora = $scope.horarios[0];
  $scope.$watch('currentPage + numPerPage', function() {
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
      , end = begin + $scope.numPerPage;

    $scope.filteredTodos = $scope.todos.slice(begin, end);
  });

});
