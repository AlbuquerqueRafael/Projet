/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").controller("menuController", function($scope, $location, menuService, $translate, $route){

  $scope.menus = menuService.getMenuAtual();
  $scope.open = false;

  $scope.$on('$routeChangeStart', function() {
    $scope.menus = menuService.getMenuAtual();
  });


  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
    console.log(langKey);

    console.log(menuService.getMenu());
    if(langKey == 'en' && menuService.getMenu() === "inicio"){
      menuService.setMenu("MenuInicialEmIngles");
    }else if(langKey == 'en' && menuService.getMenu() === "Usuario"){
      menuService.setMenu("UsuarioEmIngles");
    }else if(langKey == 'pt' && menuService.getMenu() === "MenuInicialEmIngles"){
      menuService.setMenu("inicio");
    }else if(langKey == 'pt' && menuService.getMenu() === "UsuarioEmIngles" ){
      menuService.setMenu("Usuario");
    };

    $route.reload();
    //$window.location.reload();
//    $location.path("/inicio");

  };

  $scope.rotaAtiva = function () {
    return $location.path();
  };

});
