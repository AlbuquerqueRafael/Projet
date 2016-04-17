/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").factory("menuService", function($location, $cookieStore){
  service = {};
  var menu = null;
  service.setMenu = function(tipo){
    $cookieStore.put('menu', tipo);

  }

  service.getMenu = function(){
    return $cookieStore.get('menu');
  }

  service.getMenuInicial = function(){
    menus = [{path : "/", href : "#/", nome: "Inicio", class:"home", slide:1},
      {path : "/sobre", href : "#/sobre", nome: "Sobre", class: "star", slide:2},
      {path : "/team", href : "#/team", nome: "Team", class: "glass", slide:3}
    ];

    return menus;
  };


  service.getMenuUsuario = function(){
    menus = [{path : "/horario", href : "#/horario", nome: "Home", class:"home", slide: 1},
      {path : "/cadastroHome", href : "#/cadastroHome", nome: "Cadastrar", class: "user", slide: 2},
      {path : "/busca", href : "#/busca", nome: "Pedir Carona", class: "map-marker", slide: 3},
      {path : "/notificacao", href: "#/notificacao", nome: "Notificação", class : "alert", slide: 4},
      {path : "/sair", href: "#/sair", nome: "Sair", class: "leaf", slide: 6}
    ];

    return menus;
  };

  service.getMenuAtual = function(){
    if("Usuario" === $cookieStore.get('menu')){
      return service.getMenuUsuario();
    }else{
      return service.getMenuInicial();
    }
  }

  service.getRoute = function(){
    route = "inicio";
    if($cookieStore.get('menu') === "Usuario"){
      route = "/home"
    }
    return route;
  }



  return service;
});
