/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").controller("menuController", function($scope, $location, menuService){

  $scope.menus = menuService.getMenuAtual();
  $scope.open = false;

  $scope.$on('$routeChangeStart', function() {
    $scope.menus = menuService.getMenuAtual();
  });

  $scope.rotaAtiva = function () {
    return $location.path();
  };

});
