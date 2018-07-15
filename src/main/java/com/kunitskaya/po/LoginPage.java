package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.kunitskaya.base.constants.AccountConstants.LOGIN_PAGE;

public class LoginPage extends AbstractPage {

    private static final By USERNAME_FIELD = By.id("identifierId");
    private static final By USERNAME_NEXT_BUTTON = By.id("identifierNext");
    private static final By PASSWORD_FIELD = By.name("password");
    private static final By EMAIL_FIELD = By.id("profileIdentifier");
    private static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    private static final String ENTERED_EMAIL = "data-email";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInUsername(String username) {
        webDriver.findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    public LoginPage clickUsernameNextButton() {
        webDriver.findElement(USERNAME_NEXT_BUTTON).click();
        waitForAjaxExecution();
        return this;
    }

    public String getUsernameValue() {
        WebElement profileIdentifier = webDriver.findElement(EMAIL_FIELD);
        return profileIdentifier.getAttribute(ENTERED_EMAIL);
    }

    public LoginPage fillInPassword(String password) {
        waitForElementPresence(PASSWORD_FIELD);
        webDriver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public InboxPage clickPasswordNextButton() {
        waitForElementToBeClickable(PASSWORD_NEXT_BUTTON);
        webDriver.findElement(PASSWORD_NEXT_BUTTON).click();
        waitForPageLoadComplete();
        return new InboxPage(webDriver);
    }

    public LoginPage open() {
        webDriver.get(LOGIN_PAGE);
        waitForPageLoadComplete();
        return this;
    }
}
