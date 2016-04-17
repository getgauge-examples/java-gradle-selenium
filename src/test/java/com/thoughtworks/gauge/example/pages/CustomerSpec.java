package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomerSpec {
    private final WebDriver driver;

    public CustomerSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("On the customer page")
    public void navigateToCustomersPage() {
        driver.get(CustomerPage.CustomerUrl);
    }

    @Step("Search for customer <name>")
    public void searchUser(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.searchUser(username);
    }

    @Step("The customer <name> is listed")
    public void verifyUserIsListed(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.verifyUserListed(username);
    }

    @Step("Search for customers <table>")
    public void verifyCustomers(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            searchUser(row.getCell(columnNames.get(0)));
            verifyUserIsListed(row.getCell(columnNames.get(0)));
        }
    }

    @Step("Just registered customer is listed")
    public void verifyJustRegisteredCustomerListed() {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        String currentUser = customerPage.fetchStringFromScenarioDataStore("currentUser");
        verifyUserIsListed(currentUser);
    }
}
