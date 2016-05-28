/**
 * Created by rafael on 19/04/16.
 */
angular.module("clienteApp").controller("teamCtrl", function($scope){

  $scope.membrosTime = [];
  var mafra = {nome: "João Mafra", foto:"images/team/mafra.jpg", github: "https://github.com/jvmafra",
                mail: "joao.mafra@ccc.ufcg.edu.br", facebook: "https://www.facebook.com/joaovictor.b.mafra"};

  var lucas = {nome: "Lucas", foto:"images/team/lucas.jpg", github: "https://github.com/lucaspk",
    mail: 	"jobson.silva@ccc.ufcg.edu.br", facebook: "https://www.facebook.com/jlucas.dias.1"};

  var kelvin = {nome: "Gileade Kelvin", foto:"images/team/kelvin.jpg", github: "https://github.com/gileadekelvin",
    mail:"gileade.silva@ccc.ufcg.edu.br", facebook: "https://www.facebook.com/gileadekelvin"};

  var rafael = {nome: "Rafael Albuquerque", foto:"images/team/rafa.jpg", github: "https://github.com/AlbuquerqueRafael/",
    mail: 	"rafael.sousa@ccc.ufcg.edu.br", facebook: "#/team"};  

  $scope.membrosTime.push(mafra, lucas, kelvin, rafael);


});
