package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.CreateProductPage;
import com.thoughtworks.gauge.example.pages.EditProductPage;
import com.thoughtworks.gauge.example.pages.ProductListPage;
import com.thoughtworks.gauge.example.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSpec {
    private final WebDriver driver;
    private Table table;

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

    @Step("Verify product <specifier> as <value>")
    public void verifyProduct(String specifier, String value) {
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
        productPage.verifyProductSpecifier(productPage.getWebElementByName(specifier), value);
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

    @Step("Open product edit page for stored productId")
    public void openProductEditPage() {
        EditProductPage editProductPage = PageFactory.initElements(driver, EditProductPage.class);
        driver.get(EditProductPage.EditProductUrl(editProductPage.fetchStringFromScenarioDataStore("productId")));
    }

    @Step("Update product specifier to new value <table>")
    public void updateProductValue(Table table) {
        List<List<String>> rows = table.getRows();
        for (List<String> row : rows) {
            openProductEditPage();
            EditProductPage editProductPage = PageFactory.initElements(driver, EditProductPage.class);
            editProductPage.updateProductValue(row.get(0), row.get(1));
        }
    }

    @Step("Check product specifier has new value <table>")
    public void verifyProductValue(Table table) {
        List<List<String>> rows = table.getRows();
        for (List<String> row : rows) {
            ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
            WebElement specifier = productPage.getWebElementByName((row.get(0)));
            productPage.verifyProductSpecifier(specifier, row.get(1));
        }
    }
}
