# Gauge example in Java

[![Build Status](https://travis-ci.org/getgauge/gauge-example-java.svg?branch=master)](https://travis-ci.org/getgauge/gauge-example-java)
[![HTML report](https://img.shields.io/badge/report-html-green.svg)](http://gauge-example-reports.herokuapp.com/java)
[![Console report](https://img.shields.io/badge/report-console-blue.svg)](http://gauge-example-reports.herokuapp.com/java/console)

This is an example project for doing web automation testing with [Gauge](http://getgauge.io). This project tests some of the functionalities of the [active admin demo](https://github.com/getgauge/activeadmin-demo) app. This app is hosted as a Java WAR (with embedded Jetty). 

## Running this example
The tests are run on Chrome by default.

### Prerequisites

This example requires the following softwares to run.
  * [Java 1.7](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above
    * Note that Gauge works with Java 1.6 and above. But this particular example uses Java 1.7
  * [Gauge](http://getgauge.io/get-started/index.html)
  * Gauge Java plugin
    * can be installed using `gauge --install java`
  * Chrome
  
### Setting up the System Under Test (SUT)

* Download [activeadmin-demo.war](https://bintray.com/artifact/download/gauge/activeadmin-demo/activeadmin-demo.war)
* Bring up the SUT by executing the below command
```
java -jar activeadmin-demo.war
```
* The SUT should now be available at [http://localhost:8080/](http://localhost:8080)

## Run specs

If you already have Gradle installed, you can execute specs as `gradle specs`. Otherwise, you can use the gradle wrapper and run specs as below:

### On Linux / Mac

```
./gradlew gauge
```

### On Windows

```
gradlew.bat gauge
```
This runs Gauge specs with [Gradle](http://gradle.org).

This uses Chrome as default browser for specs execution. Make sure Chrome is installed in your machine and [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) is in PATH.

If you want to use Firefox/IE as browser, pass the corresponding argument to set browser environment as follows:

```
./gradlew gauge -Penv=firefox
or
./gradlew gauge -Penv=ie
```

Note:
  * Gauge can also be used with other [build tools](http://getgauge.io/documentation/user/current/test_code/java/using_build_tools.html) like maven and ant.
  * You can use Gauge even without a build script!

## Topics covered in the example

* [Specification](http://getgauge.io/documentation/user/current/specifications/index.html), [Scenario](http://getgauge.io/documentation/user/current/specifications/scenarios.html),  [Step](http://getgauge.io/documentation/user/current/specifications/steps.html), [Concepts](http://getgauge.io/documentation/user/current/specifications/concepts.html) and [Context Steps](http://getgauge.io/documentation/user/current/specifications/contexts.html)
* [Table parameters](http://getgauge.io/documentation/user/current/specifications/parameters.html#table-parameter)
* Using [External datasource (special param)](http://getgauge.io/documentation/user/current/specifications/parameters.html#special-parameters)
* Using [tags](http://getgauge.io/documentation/user/current/specifications/tags.html)
* Using Gauge with [Selenium Webdriver](http://docs.seleniumhq.org/projects/webdriver/)
* Running Gauge specs with [Gradle](http://gradle.org)

# Copyright
Copyright 2016, ThoughtWorks Inc.
