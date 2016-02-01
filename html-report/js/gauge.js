// Copyright 2015 ThoughtWorks, Inc.

// This file is part of getgauge/html-report.

// getgauge/html-report is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// getgauge/html-report is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with getgauge/html-report.  If not, see <http://www.gnu.org/licenses/>.

marked.setOptions({ gfm: true, sanitize: true, tables: true, breaks: true, smartLists: true });

var gaugeReport = angular.module('gauge_report', ['yaru22.hovercard', 'nvd3', 'ngSanitize']).config([
    '$compileProvider',
    function ($compileProvider) {
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension|data|file):/);
    }]).config( ['$provide', function ($provide){
        $provide.decorator('$sniffer', ['$delegate', function ($delegate) {
            $delegate.history = false;
            return $delegate;
        }]);

    }]).directive('collapsable', function () {
        return function ($scope, $element) {
            $element.bind('click', function () {
                $element.parent().toggleClass("collapsed");
            });
        };
    });

function initialize() {
    (function addIndexOf() {
        if (!Array.prototype.indexOf) {
            Array.prototype.indexOf = function (obj, start) {
                for (var i = (start || 0), j = this.length; i < j; i++) {
                    if (this[i] === obj) {
                        return i;
                    }
                }
                return -1;
            };
        }
    })();
}

gaugeReport.controller('mainController', function ($scope) {
    initialize();
    $scope.result = gaugeExecutionResult.suiteResult;
    $scope.itemTypesMap = itemTypesMap;
    $scope.paramTypesMap = parameterTypesMap;
    $scope.fragmentTypesMap = fragmentTypesMap;
    $scope.dataTableIndex = 0;
    $scope.hookFailure = null;
    $scope.isPreHookFailure = false;
    $scope.conceptList = [];
    $scope.count = 0;
    $scope.isConcept = false;
    $scope.tearDownSteps= [];

    $scope.allPassed = function () {
        return !$scope.result.failed;
    };

    $scope.loadSpecification = function (specification) {
        $scope.currentSpec = specification;
    };

    $scope.initializeLightbox = initLightbox;

    $scope.setDataTableIndex = function (index) {
        $scope.dataTableIndex = index;
    };

    $scope.isRowFailure = function (index) {
        var failedRows = $scope.currentSpec.failedDataTableRows;
        if (failedRows === undefined)
            return false;
        else
            return failedRows.indexOf(index) != -1;
    };

    $scope.setCurrentStep = function (step) {
        $scope.currentStep = null;
        if (step) $scope.currentStep = step;
    };

    $scope.setCurrentConceptStep = function (step) {
        $scope.currentConceptStep = null;
        if (step) $scope.currentConceptStep = step;
    };

    $scope.setCurrentExecutionResult = function (result) {
        $scope.currentExecutionResult = null;
        if (result) $scope.currentExecutionResult = result;
    };

    $scope.setConcept = function (concept) {
        $scope.isConcept = false;
        if (concept) {
            $scope.isConcept = true;
            $scope.conceptList.push(concept);
            $scope.currentStep = concept.conceptStep;
        }
    };

    $scope.getTopConcept = function () {
        return $scope.conceptList.pop();
    };

    $scope.setCurrentScenario = function (scenario) {
        $scope.currentScenario = scenario;
    };

    $scope.getFragmentName = function (name) {
        return name || "table";
    };

    $scope.setHookFailure = function (hookFailure) {
        $scope.hookFailure = hookFailure;
    };

    $scope.hookFailureType = function () {
        return $scope.isPreHookFailure ? "Before-hook failure" : "After-hook failure";
    };

    $scope.isNewLine = function (text) {
        return text === "\n";
    };

    $scope.formattedTime = function (timeInMs, prefix) {
        if (timeInMs === undefined) return "";
        var sec = Math.floor(timeInMs / 1000);

        var min = Math.floor(sec / 60);
        sec %= 60;

        var hour = Math.floor(min / 60);
        min %= 60;

        return (prefix || "") + convertTo2Digit(hour) + ":" + convertTo2Digit(min) + ":" + convertTo2Digit(sec);
    };

    function convertTo2Digit(value) {
        if (value < 10) {
            return "0" + value;
        }
        return value;
    }

    $scope.getScreenshotSrc = function (screenshot) {
        return "data:image/png;base64," + screenshot;
    };

    $scope.sort = function (items) {
        if (!items) return true;
        var passedScenarios = [];
        var failedScenarios = [];
        return items.filter(function (item) {
            if (itemTypesMap[item.itemType] !== "Scenario") return true;
            if (item.scenario.failed) {
                failedScenarios.push(item);
            } else {
                passedScenarios.push(item);
            }
            return false;
        }).concat(failedScenarios).concat(passedScenarios);
    };

    $scope.filteredListOfSpecs = $scope.result.specResults;

    $scope.showFailedSpecs = function (){
        var specs = [];
        angular.forEach($scope.result.specResults, function(specRes){
            if (specRes.failed) {
                specs.push(specRes);
            }
        });
        $scope.setCurrentSpec(true, specs[0]);
        $scope.filteredListOfSpecs = specs;
    };

    $scope.showPassedSpecs = function (){
        var specs = [];
        angular.forEach($scope.result.specResults, function(specRes){
            if (!specRes.failed && !specRes.skipped) {
                specs.push(specRes);
            }
        });
        $scope.loadSpecification(specs[0]);
        $scope.filteredListOfSpecs = specs;
    };

    $scope.showSkippedSpecs = function (){
        var specs = [];
        angular.forEach($scope.result.specResults, function(specRes){
            if (specRes.skipped) {
                specs.push(specRes);
            }
        });
        $scope.loadSpecification(specs[0]);
        $scope.filteredListOfSpecs = specs;
    };

    $scope.showAllSpecs = function (){
        $scope.loadSpecification($scope.result.specResults[0]);
        $scope.filteredListOfSpecs = $scope.result.specResults;
    };

    $scope.setCurrentSpec = function (isFirst, specResult) {
        if (isFirst && specResult.failed)
            $scope.currentSpec = specResult;
    };

    $scope.addTearDownSteps= function (steps) {
        $scope.tearDownSteps = steps;
    };

    $scope.getStatus = function (step) {
        if (step.stepExecutionResult.skipped)
            return "skipped";
        if (step.stepExecutionResult.executionResult)
            return step.stepExecutionResult.executionResult.failed;
        return undefined;
    };

    $scope.getScenarioStatus = function (scenario) {
        if (scenario.skipped)
            return "skipped";
        return scenario.failed;
    };

    $scope.summaryItems = [{
        "key": "Environment",
        "value": $scope.result.environment
    }, {
        "key": "Tags",
        "value": $scope.result.tags
    }, {
        "key": "Success Rate",
        "value": $scope.result.successRate + "%"
    }, {
        "key": "Total Time",
        "value": $scope.formattedTime($scope.result.executionTime)
    }, {
        "key": "Generated On",
        "value": $scope.result.timestamp
    }];

    $scope.isEmpty = function (item) {
        return !!(typeof(item) === "string" && item.length <= 0);
    };

    $scope.searchItems = function (searchQuery) {
        return function (spec) {
            if (!searchQuery) return true;
            var tagMatches = spec.protoSpec.items.filter(function (item) {
                var searchList = [];
                if (item.scenario) searchList.push(item.scenario.scenarioHeading);
                if (item.scenario && item.scenario.tags) searchList = searchList.concat(item.scenario.tags);
                if (item.tags && item.tags.tags) searchList = searchList.concat(item.tags.tags);
                return searchList.join(" ").toLowerCase().indexOf(searchQuery.toLowerCase()) > -1;
            });
            if (tagMatches.length) return true;
            return spec.protoSpec.specHeading.toLowerCase().indexOf(searchQuery.toLowerCase()) > -1;
        };
    };

    $scope.showScenario = function (item) {
        if (!$scope.searchQuery) return true;
        if (item.contexts && item.contexts.length) {
            var matchedContexts = item.contexts.filter(function (context) {
                if (context.step && context.step.parsedText) {
                    return context.step.parsedText.indexOf($scope.searchQuery.toLowerCase()) > -1;
                }
                return false;
            });
            if (matchedContexts.length > 0) return true;
        }
        if (item.scenarioHeading.toLowerCase().indexOf($scope.searchQuery.toLowerCase()) < 0) {
            if (item.tags) return item.tags.join(" ").toLowerCase().indexOf($scope.searchQuery.toLowerCase()) > -1;
        } else {
            return true;
        }
        return false;
    };

    var myColors = ["#27caa9", "#e73e48", "#999999"];
    d3.scale.myColors = function () {
        return d3.scale.ordinal().range(myColors);
    };

    $scope.options = {
        chart: {
            type: 'pieChart',
            height: 200,
            margin : {
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            },
            donut: true,
            donutRatio: 0.2,
            x: function (d) {
                return d.label;
            },
            y: function (d) {
                return d.score;
            },
            showLabels: false,
            showValues: true,
            transitionDuration: 500,
            labelThreshold: 0.01,
            color: d3.scale.myColors().range(),
            showLegend: false,
            valueFormat: d3.format("d")
        }
    };

    $scope.totalSpecs = $scope.result && $scope.result.specResults ? $scope.result.specResults.length : 0;
    $scope.passed = 0;
    $scope.failed = 0;
    $scope.skipped = 0;

    if ($scope.result && $scope.result.specResults) {
        $scope.result.specResults.forEach(function (spec) {
            if (spec.skipped) $scope.skipped++;
            if (spec.failed) $scope.failed++;
            if (!spec.skipped && !spec.failed) $scope.passed++;
        });
    } else if ($scope.result && $scope.result.preHookFailure) {
        $scope.hookFailure = $scope.result.preHookFailure;
        $scope.isPreHookFailure = true;
    }

    $scope.projectName = $scope.result.projectName;

    $scope.data = [
        {
            label: "Passed",
            score: $scope.passed
        },
        {
            label: "Failed",
            score: $scope.failed
        },
        {
            label: "Skipped",
            score: $scope.skipped
        }
    ];

    $scope.parseComments = function (items) {
        if (!items || !items.length) return null;
        return marked(items.map(function (x) { return x.comment && x.comment.text; }).join("\n").trim().split("\n").join("\n"));
    };

});
