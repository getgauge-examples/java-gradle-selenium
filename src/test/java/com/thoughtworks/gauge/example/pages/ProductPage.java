package com.thoughtworks.gauge.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends BasePage {

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(2) td")
    public WebElement title;

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(3) td")
    public WebElement description;

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(4) td")
    public WebElement author;

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(5) td")
    public WebElement price;

    @FindBy(how = How.CSS, css = "#titlebar_right div.action_items span.action_item:nth-child(2) a")
    public WebElement deleteButton;

    public void verifyAuthor(String name) {
        Assert.assertTrue(author.getText().equals(name));
    }

    public void delete() {
        deleteButton.click();
    }
}
