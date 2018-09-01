package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.test.selenium.Languages;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementVisibility;

public class LoginPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://mail.google.com/mail/";

    private static final String LANGUAGE_ATTRIBUTE = "data-value";

    @FindBy(id = "identifierId")
    WebElement usernameField;

    @FindBy(id = "identifierNext")
    WebElement usernameNextButton;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "passwordNext")
    WebElement passwordNextButton;

    @FindBy(xpath = "//div[@id='password']//div[@aria-live='assertive']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[@aria-selected='true']")
    WebElement selectedLanguage;

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
        waitForElementVisibility(webDriver, passwordField);
        return this;
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
        try {
            waitForElementVisibility(webDriver, errorMessage);
            return StringUtils.containsIgnoreCase(errorMessage.getText(), expectedErrorMessage);
        } catch (ElementNotVisibleException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        waitForElementVisibility(webDriver, errorMessage);
        return errorMessage.getText();
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }

    public Languages getDisplayedLanguage() {
        return Languages.getLanguage(selectedLanguage.getAttribute(LANGUAGE_ATTRIBUTE));
    }
}
