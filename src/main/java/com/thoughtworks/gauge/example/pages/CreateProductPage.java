package com.thoughtworks.gauge.example.pages;

import org.openqa.selenium.WebElement;

public class CreateProductPage extends BasePage {
    public static String NewProductUrl = Url.concat("admin/products/new/");

    public WebElement product_title;

    public WebElement product_description;

    public WebElement product_author;

    public WebElement product_price;

    public WebElement commit;

    public WebElement product_image_file_name;

    public void create(String title, String desc, String author, String price) {
        product_title.sendKeys(title);
        product_description.sendKeys(desc);
        product_author.sendKeys(author);
        product_price.sendKeys(price);
        product_image_file_name.sendKeys("not available");
        commit.click();
    }
}
