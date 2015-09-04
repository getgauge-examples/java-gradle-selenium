package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.*;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.ProductPage;
import com.thoughtworks.gauge.example.pages.ProductListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ExecutionHooks {

    private final WebDriver driver;
    private Table table;

    public ExecutionHooks() {
        this.driver=DriverFactory.getDriver();
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
