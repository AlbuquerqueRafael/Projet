/**
 * Created by rafael on 19/04/16.
 */
angular.module("clienteApp").controller("teamCtrl", function($scope){

  $scope.membrosTime = [];
  var mafra = {nome: "João Mafra", foto:"images/team/mafra.jpg", github: "https://github.com/jvmafra",
                mail: "joao.mafra@ccc.ufcg.edu.br", facebook: "#"};

  var lucas = {nome: "Lucas", foto:"images/team/lucas.jpg", github: "https://github.com/jvmafra",
    mail: 	"joao.mafra@ccc.ufcg.edu.br", facebook: "#"};

  var kelvin = {nome: "Gileade Kelvin", foto:"images/team/kelvin.jpg", github: "https://github.com/jvmafra",
    mail:"joao.mafra@ccc.ufcg.edu.br", facebook: "#"};

  var rafael = {nome: "Rafael Albuquerque", foto:"images/team/rafa.jpg", github: "https://github.com/AlbuquerqueRafael/",
    mail: 	"rafael.sousa@ccc.ufcg.edu.br", facebook: "#/team"};

  var leandro = {nome: "Leandro", foto:"images/team/leandro.jpg", github: "https://github.com/jvmafra",
    mail: 	"joao.mafra@ccc.ufcg.edu.br", facebook: "#"};

  $scope.membrosTime.push(mafra, lucas, kelvin, rafael, leandro);


});
