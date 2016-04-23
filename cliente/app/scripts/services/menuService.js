/**
 * Created by rafael on 05/04/16.
 */

'use strict';

angular.module("clienteApp").factory("menuService", function($location, $cookieStore){
  var service = {};
  var menu = null;
  service.setMenu = function(tipo){
    $cookieStore.put('menu', tipo);

  };

  service.getMenu = function(){
    return $cookieStore.get('menu');
  };

  service.getMenuInicial = function(){
    var menus = [{path : "/", href : "#/", nome: "Inicio", class:"home", slide:1},
      {path : "/sobre", href : "http://bit.ly/1VIe2p0", nome: "Sobre", class: "star", slide:2},
      {path : "/team", href : "#/team", nome: "Team", class: "glass", slide:3}
    ];

    return menus;
  };


  service.getMenuUsuario = function(){
    var menus = [{path : "/home", href : "#/home", nome: "Home", class:"home"},
      {path : "/horario", href : "#/horario", nome: "Horario", class:"time"},
      {path : "/cadastroHome", href : "#/cadastroHome", nome: "Cadastrar", class: "user"},
      {path : "/buscar", href : "#/buscar", nome: "Pedir Carona", class: "map-marker"},
      {path : "/notificacao", href: "#/notificacao", nome: "Notificação", class : "alert"},
      {path : "/sair", href: "#/sair", nome: "Sair", class: "leaf"}
    ];

    return menus;
  };

  service.getMenuAtual = function(){
    if("Usuario" === $cookieStore.get('menu')){
      return service.getMenuUsuario();
    }else{
      return service.getMenuInicial();
    }
  };

  service.getRoute = function(){
    var route = "inicio";
    if($cookieStore.get('menu') === "Usuario"){
      route = "/home";
    }
    return route;
  };



  return service;
});
