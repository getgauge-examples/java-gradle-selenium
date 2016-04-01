package com.thoughtworks.gauge.example.pages;

import org.openqa.selenium.WebElement;

public class SignUpPage extends BasePage {
    public static String SignUpUrl = Url.concat("signup");

    public WebElement user_username;

    public WebElement user_email;

    public WebElement user_password;

    public WebElement user_password_confirmation;

    public WebElement commit;
}
