# Gauge example project, in Java

[![Build Status](https://snap-ci.com/getgauge/gauge-example-java/branch/master/build_image)](https://snap-ci.com/getgauge/gauge-example-java/branch/master)

###Last Run Reports (Powered by [Snap CI](https://snap-ci.com/))
- [Console](https://gauge-reports-java.herokuapp.com/console)
- [Html](https://gauge-reports-java.herokuapp.com/)

This project serves as an example for writing Automation using [Gauge](https://github.com/getgauge/gauge)

# Concepts covered

- Use [Webdriver](http://docs.seleniumhq.org/projects/webdriver/) as base of implementation
- [Specification](http://getgauge.io/documentation/user/current/specifications/README.html), [Scenario](http://getgauge.io/documentation/user/current/specifications/scenarios.html) & [Step](http://getgauge.io/documentation/user/current/specifications/steps.html) usage
- [Concepts](http://getgauge.io/documentation/user/current/specifications/concepts.html)
- [Table parameters](http://getgauge.io/documentation/user/current/specifications/parameters.html#table-parameter)
- [External datasource (special param)](http://getgauge.io/documentation/user/current/specifications/parameters.html#special-parameters)


# Prerequisites
- [Install Gauge](http://getgauge.io/download.html)
- [Install Gauge-Java plugin](http://getgauge.io/documentation/user/current/plugins/installation.html) by running ```gauge --install java```
- Get the latest version of [Selenium Server Standalone Jar](http://selenium-release.storage.googleapis.com/index.html) This should be added as a dependency in the project under ```libs``` directory.
- [Chrome browser executable](https://sites.google.com/a/chromium.org/chromedriver/downloads) should be in ```PATH``` for executing against chrome browser.
- [Gauge Intellij plugin](https://plugins.jetbrains.com/plugin/7535) (optional, but recommended)

# Executing specs

## Command line
### All specs
````
gauge specs/
````

### Run against chrome browser
````
gauge --env chrome specs/
````

# Copyright
Copyright 2015, ThoughtWorks Inc.
