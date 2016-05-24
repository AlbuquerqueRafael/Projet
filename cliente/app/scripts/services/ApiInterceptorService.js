
angular.module("clienteApp").factory("errorInterceptor", function ($q, $location, $cookieStore, menuService) {
  return {
    responseError: function (rejection) {
     if (rejection.status === 404) {
        $cookieStore.remove('login');
        $location.path("/");
      }

      if (rejection.status === 401) {
        menuService.setMenu("inicio");
        $cookieStore.remove('login');
        $location.path("/");
      }

      return $q.reject(rejection);
    }
  };
});
