'use strict';


module.exports = ['$scope', '$http', function ($scope, $http) {

    $http.get('load/today').then(function (resp) {
        $scope.load = resp.data;
    });
}];