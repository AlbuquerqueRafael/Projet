/**
 * Created by rafael on 23/05/16.
 */
angular.module("clienteApp").config(function ($httpProvider) {
  $httpProvider.interceptors.push("errorInterceptor");
});
