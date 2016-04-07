/**
 * Created by rafael on 04/04/16.
 */

angular.module("clienteApp").directive('jqdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
                dateFormat: 'dd/mm/yy',
                onSelect: function (data) {
                    scope.data = data;
                    scope.$apply();
                }
            });
        }
    }
});
