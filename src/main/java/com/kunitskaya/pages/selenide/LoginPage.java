package com.kunitskaya.pages.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public static final By USERNAME_FIELD = By.id("identifierId");
    public static final By USERNAME_VALUE = By.id("profileIdentifier");
    public static final By PASSWORD_FIELD = By.name("password");

    public static void enterUsername(String username) {
        $(USERNAME_FIELD).setValue(username)
                         .pressEnter();
    }

    public static void enterPassword(String password) {
        $(PASSWORD_FIELD).setValue(password)
                         .pressEnter();
    }
}
