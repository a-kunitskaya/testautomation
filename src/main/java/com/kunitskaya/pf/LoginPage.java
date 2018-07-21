package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class LoginPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://www.gmail.com";

    private static final String ENTERED_EMAIL_ATTRIBUTE = "data-email";

    @FindBy(id = "identifierId")
    WebElement usernameField;

    @FindBy(id = "identifierNext")
    WebElement usernameNextButton;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "profileIdentifier")
    WebElement emailField;

    @FindBy(id = "passwordNext")
    WebElement passwordNextButton;

    public LoginPage fillInUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage clickUsernameNextButton() {
        usernameNextButton.click();
        return this;
    }

    public String getUsernameValue() {
        return emailField.getAttribute(ENTERED_EMAIL_ATTRIBUTE);
    }

    public LoginPage fillInPassword(String password) {
        waitForElementVisibility(webDriver, passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public MailPage clickPasswordNextButton() {
        waitForElementToBeClickable(webDriver, passwordNextButton);
        passwordNextButton.click();
        return new MailPage();
    }

    public LoginPage open() {
        webDriver.get(LOGIN_PAGE_URL);
        return this;
    }
}
