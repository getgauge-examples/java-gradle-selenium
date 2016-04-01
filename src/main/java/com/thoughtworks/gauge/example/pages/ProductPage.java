package com.thoughtworks.gauge.example.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    @FindBy(how = How.CSS, css = "#main_content table tbody tr:nth-child(1) td")
    public WebElement productId;

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

    public WebElement getWebElementByName(String elementName) {
        switch (elementName) {
            case "title":
                return title;
            case "description":
                return description;
            case "author":
                return author;
            case "price":
                return price;
        }
        return null;
    }

    public void verifyProductSpecifier(WebElement specifier, String value) {
        Assert.assertTrue(specifier.getText().equals(value));
    }

    public void delete(WebDriver driver) {
        deleteButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
