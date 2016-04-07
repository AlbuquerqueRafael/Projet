/**
 * Created by rafael on 05/04/16.
 */
angular.module("clienteApp").factory("menuService", function($location, $cookieStore){
  service = {};
  var menu = null;
  service.setMenu = function(tipo){
    $cookieStore.put('menu', tipo);

  }

  service.getMenuInicial = function(){
    menus = [{path : "/", href : "#/", nome: "Inicio", class:"home", slide:1},
      {path : "/sobre", href : "#/sobre", nome: "Sobre", class: "star", slide:2},
      {path : "/team", href : "#/team", nome: "Team", class: "glass", slide:3}
    ];

    return menus;
  };

  service.getMenuPassageiro= function(){
    menus = service.getMenuMotorista();
    menus.splice(1, 0, {path : "/busca", href : "#/busca", nome: "Buscar", class: "search", slide: 6});

    return menus;
  };

  service.getMenuMotorista = function(){
    menus = [{path : "/home", href : "#/inicio", nome: "Home", class:"home", slide: 1},
      {path : "/cadastroHome", href : "#/cadastroHome", nome: "Cadastrar", class: "user", slide: 2},
      {path : "/horario", href : "#/horario", nome: "Horario", class: "time", slide: 3},
      {path : "/notificacao", href: "#/notificacao", nome: "Notificação", class : "alert", slide: 4},
      {path : "/sair", href: "#/sair", nome: "Sair", class: "leaf", slide: 5}
    ];

    return menus;
  };

  service.getMenuAtual = function(){
    if("Passageiro" === $cookieStore.get('menu')){
      return service.getMenuPassageiro();
    }else if("Motorista" === $cookieStore.get('menu')){
      return service.getMenuMotorista();
    }else{
      return service.getMenuInicial();
    }
  }

  service.getRoute = function(){
    route = "inicio";
    if($cookieStore.get('menu') == "logado"){
      route = "/home"
    }
    return route;
  }



  return service;
});
