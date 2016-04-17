package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ExecutionHooks {

    private final WebDriver driver;

    public ExecutionHooks() {
        this.driver = DriverFactory.getDriver();
    }

    @BeforeScenario(tags = {"edit"})
    public void findAndStoreProductId() {
        driver.get(ProductListPage.ProductsUrl);
        ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
        productListPage.search("The Way to Go");
        productListPage.openFirstProduct();
        productListPage.storeStringToScenarioDataStore("productId", productListPage.productId.getText());
    }

    @AfterSuite
    public void deleteTestProducts() {

    }
}
