/**
 * Created by rafael on 21/04/16.
 */

'use strict';

angular.module("clienteApp").controller("homeCtrl", function($scope, Upload, $http){

  $scope.getFoto = function (file) {
    Upload.upload({
      url: 'images',
      data: {file: file}
    }).then(function (resp) {
      console.log(resp);
      $scope.foto = resp.config.data;
    }, function (resp) {
      console.log('Erro: ' + resp.status);
    }, function (evt) {
      var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
      console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
    });
  };

  // Function default do modullo ng-file
  $scope.upload = function (file) {
     $http.post("/app/updateFoto").success(function(info){
       console.log(info);
     }).error(function(erro){
       console.log(erro);
      });

  };

});
