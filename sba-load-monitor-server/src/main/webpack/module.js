/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
'use strict';

var angular = require('angular');

var module = angular.module('sba-load-monitor', []);
global.sbaModules.push(module.name);

module.directive('lineChart', require('./lineDirective'));
module.controller('loadMonitorCtrl', require('./loadMonitorCtrl.js'));

module.config(function ($stateProvider) {
    $stateProvider.state('load-monitor', {
        url: '/load-monitor',
        templateUrl: 'load-monitor/load-monitor.html',
        controller: 'loadMonitorCtrl'
    });
});

module.run(function (MainViews) {
    MainViews.register({
        title: 'Load',
        state: 'load-monitor',
        order: 99
    });
});
