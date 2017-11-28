'use strict';


module.exports = [function () {
    return {
        restrict: 'AE',
        scope: {
            data: '=data',
            name: '=name'
        },
        template: '<div>{{ name }}</div><div style="height:400px"></div>',
        link: function (scope, element) {
            var echarts = require('echarts');
            var chart = echarts.init(element.children()[1]);

            var series = scope.data.map(function (x) {
                var time = new Date(x.minute * 60000);
                return {
                    name: time.toString(),
                    value: [time, x.count]
                };
            });

            var option = {
                title: {
                    text: name
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        params = params[0];
                        var date = new Date(params.name);
                        return date.toString();
                    },
                    axisPointer: {
                        animation: false
                    }
                },
                xAxis: {
                    type: 'time',
                    splitLine: {
                        show: false
                    }
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%'],
                    splitLine: {
                        show: false
                    }
                },
                series: [{
                    name: 'load',
                    type: 'line',
                    showSymbol: false,
                    hoverAnimation: false,
                    data: series
                }]
            };
            chart.setOption(option);
        }
    };
}];