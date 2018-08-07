package com.kunitskaya.selenide;

import com.codeborne.selenide.Configuration;
import com.kunitskaya.business.objects.User;
import com.kunitskaya.test.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.kunitskaya.pages.LoginPage.LOGIN_PAGE_URL;

public class SelenideTest {
    private static final By USERNAME_FIELD = By.id("identifierId");
    private static final By USERNAME_VALUE = By.id("profileIdentifier");
    private static final By PASSWORD_FIELD = By.name("password");
    private static final By ACCOUNT_ICON = By.cssSelector(".gb_b.gb_eb.gb_R");

    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    public void logInTest() {
        User user = TestDataProvider.getUser();
        String username = user.getUsername();
        String password = user.getPassword();

        open(LOGIN_PAGE_URL);

        //enter username, proceed
        $(USERNAME_FIELD).setValue(username)
                                .pressEnter();

        //assert that the entered username == user username
        $(USERNAME_VALUE).shouldHave(text(username));

        //enter password, proceed
        $(PASSWORD_FIELD).setValue(password)
                              .pressEnter();

        //assert that account icon is displayed
        $(ACCOUNT_ICON).shouldBe(visible);
    }
}
