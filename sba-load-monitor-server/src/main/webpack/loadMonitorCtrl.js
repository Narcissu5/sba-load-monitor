'use strict';


module.exports = ['$scope', '$http', '$interval', function ($scope, $http, $interval) {


    $http.get('load/today').then(function (resp) {
        $scope.load = resp.data.loads;
    });

    $interval(function () {
        $http.get('load/current').then(function (resp) {
            Object.keys(resp.data).forEach(function (key) {
                if ($scope.load[key]) {
                    $scope.load[key] = $scope.load[key].concat(resp.data[key]);
                } else {
                    $scope.load[key] = resp.data[key];
                }
            });
        });
    }, 60000);
}];