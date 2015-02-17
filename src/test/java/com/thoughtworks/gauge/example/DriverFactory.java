package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static FirefoxDriver getDriver() {
        return driver;
    }

    private static FirefoxDriver driver;

    @BeforeSuite
    public void Setup() {
        driver = new FirefoxDriver();
    }

    @AfterSuite
    public void TearDown() {
        driver.close();
    }
}
