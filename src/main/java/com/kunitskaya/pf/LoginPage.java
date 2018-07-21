package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.constants.AccountConstants.LOGIN_PAGE_URL;

public class LoginPage extends AbstractPage {

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

    protected LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

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
        waitForElementVisibility(passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public MailPage clickPasswordNextButton() {
        waitForElementToBeClickable(passwordNextButton);
        passwordNextButton.click();
        return new MailPage(webDriver);
    }

    public LoginPage open() {
        webDriver.get(LOGIN_PAGE_URL);
        return this;
    }
}
