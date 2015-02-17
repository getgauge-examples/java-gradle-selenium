package com.thoughtworks.gauge.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductListPage extends BasePage{
    public static String ProductsUrl = Url.concat("products/");
    
    public WebElement q_title;
    
    public WebElement q_submit;

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(1) td.product a")
    public WebElement firstProduct;
    
    public void search(String name) {
        q_title.sendKeys(name);
        q_submit.click();
    }

    public void openFirstProduct() {
        firstProduct.click();
    }
}
