package com.kunitskaya.selenide;

import com.codeborne.selenide.Configuration;
import com.kunitskaya.business.objects.User;
import com.kunitskaya.test.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.kunitskaya.pages.LoginPage.LOGIN_PAGE_URL;

public class SelenideTest {
    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    public void logIn() {
        User user = TestDataProvider.getUser();
        open(LOGIN_PAGE_URL);
        $(By.id("identifierId"))
                .setValue(user.getUsername())
                .pressEnter();

        $(By.name("password"))
                .setValue(user.getPassword())
                .pressEnter();
    }
}
