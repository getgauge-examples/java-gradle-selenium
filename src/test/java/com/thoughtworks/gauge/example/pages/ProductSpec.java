package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSpec {
    private final WebDriver driver;
    private Table table;

    public ProductSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("Create a product <table>")
    public void CreateProduct(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            openNewProductsPage();
            CreateProductPage createProductPage = PageFactory.initElements(driver, CreateProductPage.class);
            createProductPage.create(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), row.getCell(columnNames.get(3)));
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
        productPage.delete(driver);
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
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            openProductEditPage();
            EditProductPage editProductPage = PageFactory.initElements(driver, EditProductPage.class);
            editProductPage.updateProductValue(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)));
        }
    }

    @Step("Check product specifier has new value <table>")
    public void verifyProductValue(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
            WebElement specifier = productPage.getWebElementByName((row.getCell(columnNames.get(0))));
            productPage.verifyProductSpecifier(specifier, row.getCell(columnNames.get(1)));
        }
    }
}
