package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.CreateProductPage;
import com.thoughtworks.gauge.example.pages.ProductListPage;
import com.thoughtworks.gauge.example.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSpec {
    private final WebDriver driver;

    public ProductSpec() {
        this.driver=DriverFactory.getDriver();
    }

    @Step("Create a product <table>")
    public void CreateProduct(Table table) {
        List<List<String>> rows = table.getRows();
        for (List<String> row : rows) {
            openNewProductsPage();
            CreateProductPage createProductPage = PageFactory.initElements(driver, CreateProductPage.class);
            createProductPage.create(row.get(0),row.get(1), row.get(2), row.get(3));
        }
    }

    @Step("On product page")
    public void openProductsPage() {
        driver.get(ProductListPage.ProductsUrl);
    }

    @Step("Search for product <name>")
    public void searchProduct(String title) {
        ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
        productListPage.search(title);
    }
    @Step("Open description for product <name>")
    public void viewProductDescription(String name) {
        ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
        productListPage.openFirstProduct();
    }
    
    @Step("Verify product author as <author>")
    public void verifyProductTitle(String author) {
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
        productPage.verifyAuthor(author);
    }

    @Step("Delete this product")
    public void deleteProduct() {
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
        productPage.delete();
    }

    @Step("On new products page")
    public void openNewProductsPage() {
        driver.get(CreateProductPage.NewProductUrl);
    }
}
