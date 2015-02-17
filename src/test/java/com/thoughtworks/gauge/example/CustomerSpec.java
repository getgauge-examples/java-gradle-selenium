package com.thoughtworks.gauge.example;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.pages.CustomerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CustomerSpec {
    private final WebDriver driver;

    public CustomerSpec() {
        this.driver=DriverFactory.getDriver();
    }

    @Step("On the customer page")
    public void NavigateToCustomersPage() {
        driver.get(CustomerPage.CustomerUrl);
    }

    @Step("Search for customer <name>")
    public void SearchUser(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.searchUser(username);
    }

    @Step("The customer <name> is listed")
    public void VerifyUserIsListed(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.verifyUserListed(username);
    }
}
