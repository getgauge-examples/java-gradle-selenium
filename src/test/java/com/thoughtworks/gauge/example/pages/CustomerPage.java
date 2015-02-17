package com.thoughtworks.gauge.example.pages;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CustomerPage extends BasePage {
    public static String CustomerUrl = Url.concat("customers/");

    public WebElement q_username;
    
    public WebElement q_submit;
    
    @FindBy(how= How.CSS, css = "table#customers tbody tr:nth-child(1) td.username")
    public WebElement usernameResult;
    
    public void searchUser(String username) {
        q_username.clear();
        q_username.sendKeys(username);
        q_submit.click();
    }

    public void verifyUserListed(String username) {
        Assert.assertTrue(usernameResult.getText().equals(username));
    }
}
