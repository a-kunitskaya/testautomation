package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementPresence;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;

public class LoginPage extends AbstractPage {

    public static final String LOGIN_PAGE_URL = "https://www.gmail.com";

    private static final By USERNAME_FIELD = By.id("identifierId");
    private static final By USERNAME_NEXT_BUTTON = By.id("identifierNext");
    private static final By PASSWORD_FIELD = By.name("password");
    private static final By EMAIL_FIELD = By.id("profileIdentifier");
    private static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    private static final String ENTERED_EMAIL_ATTRIBUTE = "data-email";


    public LoginPage fillInUsername(String username) {
        webDriver.findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    public LoginPage clickUsernameNextButton() {
        webDriver.findElement(USERNAME_NEXT_BUTTON).click();
        return this;
    }

    public String getUsernameValue() {
        WebElement profileIdentifier = webDriver.findElement(EMAIL_FIELD);
        return profileIdentifier.getAttribute(ENTERED_EMAIL_ATTRIBUTE);
    }

    public LoginPage fillInPassword(String password) {
        waitForElementPresence(webDriver, PASSWORD_FIELD);
        webDriver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public MailPage clickPasswordNextButton() {
        waitForElementToBeClickable(webDriver, PASSWORD_NEXT_BUTTON);
        webDriver.findElement(PASSWORD_NEXT_BUTTON).click();
        return new MailPage();
    }

    public LoginPage open() {
        webDriver.get(LOGIN_PAGE_URL);
        return this;
    }
}
