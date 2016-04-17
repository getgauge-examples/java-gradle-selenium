package com.thoughtworks.gauge.example.pages;

import org.openqa.selenium.WebElement;

public class EditProductPage extends ProductPage {

    public WebElement product_title;
    public WebElement product_description;
    public WebElement product_author;
    public WebElement product_price;
    public WebElement product_featured;
    public WebElement product_available_on_1i;
    public WebElement product_available_on_2i;
    public WebElement product_available_on_3i;
    public WebElement product_image_file_name;
    public WebElement commit;

    public static String EditProductUrl(String productId) {
        return Url.concat("admin/products/" + productId + "/edit");
    }

    public void saveProduct() {
        commit.click();
    }

    public void updateProductValue(String specifier, String newValue) {
        switch (specifier.toLowerCase()) {
            case "title":
                product_title.clear();
                product_title.sendKeys(newValue);
                break;
            case "description":
                product_description.clear();
                product_description.sendKeys(newValue);
                break;
            case "author":
                product_author.clear();
                product_author.sendKeys(newValue);
                break;
            case "price":
                product_price.clear();
                product_price.sendKeys(newValue);
                break;
            case "imageFileName":
                product_image_file_name.clear();
                product_image_file_name.sendKeys(newValue);
                break;
        }
        saveProduct();
    }
}
