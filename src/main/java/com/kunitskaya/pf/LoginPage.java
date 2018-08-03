package com.kunitskaya.pf;

import com.kunitskaya.base.waits.ExplicitWait;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class LoginPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://mail.google.com/mail/";
    public static final String WRONG_PASSWORD_ERROR_MESSAGE = "Wrong password. Try again or click Forgot password to reset it.";

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

    @FindBy(xpath = "//div[contains(text(), 'Wrong password')]")
    WebElement errorMessage;

    public LoginPage() {
        super();
    }

    public LoginPage fillInUsername(String username) {
        highlightElement(usernameField);
        usernameField.sendKeys(username);
        unHighlightElement(usernameField);
        return this;
    }

    public LoginPage clickUsernameNextButton() {
        new Actions(webDriver).click(usernameNextButton).build().perform();
        ExplicitWait.waitForElementVisibility(webDriver, passwordField);
        return this;
    }

    public String getUsernameValue() {
        return emailField.getText();
    }

    public LoginPage fillInPassword(String password) {
        waitForElementVisibility(webDriver, passwordField);
        highlightElement(passwordField);
        passwordField.sendKeys(password);
        unHighlightElement(passwordField);
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

    public boolean isErrorMessageDisplayed(String expectedErrorMessage) {
        return StringUtils.containsIgnoreCase(errorMessage.getText(), expectedErrorMessage);
    }
}
