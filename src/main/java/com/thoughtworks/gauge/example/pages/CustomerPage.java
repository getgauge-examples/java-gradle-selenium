package com.thoughtworks.gauge.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class CustomerPage extends BasePage {
    public static String CustomerUrl = Url.concat("admin/customers/");

    public WebElement q_username;

    public WebElement commit;

    @FindBy(how = How.CSS, css = "table#index_table_customers tbody tr:nth-child(1) td.col-username")
    public WebElement usernameResult;

    public void searchUser(String username) {
        q_username.clear();
        q_username.sendKeys(username);
        commit.click();
    }

    public void verifyUserListed(String username) {
        assertEquals(username, usernameResult.getText());
    }
}
