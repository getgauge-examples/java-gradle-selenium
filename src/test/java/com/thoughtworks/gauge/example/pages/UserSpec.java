package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;


public class UserSpec {
    private final WebDriver driver;

    public UserSpec() {
        this.driver = DriverFactory.getDriver();
    }

    public String localPart() {
        // Creating a random local part of an email address also used as username
        return UUID.randomUUID().toString();
    }

    @Step("On signup page")
    public void navigateToSignUpPage() {
        driver.get(SignUpPage.SignUpUrl);
    }

    @Step("Fill in and send registration form")
    public void searchUser() {
        String username = localPart();
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.user_username.sendKeys(username);
        signUpPage.user_email.sendKeys(username.concat("@domain.com"));
        signUpPage.user_password.sendKeys("qweqwe");
        signUpPage.user_password_confirmation.sendKeys("qweqwe");
        signUpPage.commit.submit();
        // store generated username
        signUpPage.storeStringToScenarioDataStore("currentUser", username);
    }
}
