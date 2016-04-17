package com.thoughtworks.gauge.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductListPage extends ProductPage {
    public static String ProductsUrl = Url.concat("admin/products/");

    public WebElement q_title;

    public WebElement commit;

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(1) td.product a")
    public WebElement firstProduct;

    public void search(String name) {
        q_title.sendKeys(name);
        commit.click();
    }

    public void openFirstProduct() {
        firstProduct.click();
    }
}
