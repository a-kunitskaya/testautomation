package com.kunitskaya.po;

import org.openqa.selenium.By;

public class LogoutPage extends AbstractPage {
    private static final By PASSWORD_FIELD = By.name("password");

    public boolean isPasswordFieldDisplayed() {
        return webDriver.findElement(PASSWORD_FIELD).isDisplayed();
    }
}
