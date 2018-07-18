package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.constants.AccountConstants.LOGIN_PAGE;

public class LoginPageFactory extends AbstractPageFactory {

    private static final String ENTERED_EMAIL = "data-email";

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

    protected LoginPageFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPageFactory fillInUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPageFactory clickUsernameNextButton() {
        usernameNextButton.click();
        waitForAjaxExecution();
        return this;
    }

    public String getUsernameValue() {
        return emailField.getAttribute(ENTERED_EMAIL);
    }

    public LoginPageFactory fillInPassword(String password) {
        waitForElementVisibility(passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public BaseLoggedInPageFactory clickPasswordNextButton() {
        waitForElementToBeClickable(passwordNextButton);
        passwordNextButton.click();
        waitForPageLoadComplete();
        return new BaseLoggedInPageFactory(webDriver);
    }

    public LoginPageFactory open() {
        webDriver.get(LOGIN_PAGE);
        waitForPageLoadComplete();
        return this;
    }
}
