package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends AbstractPage {
    private static final By PASSWORD_FIELD = By.name("password");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPasswordFieldDislayed() {
        return webDriver.findElement(PASSWORD_FIELD).isDisplayed();
    }
}
