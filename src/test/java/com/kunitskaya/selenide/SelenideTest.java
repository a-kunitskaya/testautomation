package com.kunitskaya.selenide;

import com.codeborne.selenide.Configuration;
import com.kunitskaya.business.objects.User;
import com.kunitskaya.test.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
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
        String username = user.getUsername();
        String password = user.getPassword();

        open(LOGIN_PAGE_URL);

        //enter username, proceed
        $(By.id("identifierId")).setValue(username)
                                .pressEnter();

        //assert that the entered username == user username
        $(By.id("profileIdentifier")).shouldHave(text(username));

        //enter password, proceed
        $(By.name("password")).setValue(password)
                              .pressEnter();

        //assert that the entered password == user password

    }
}


//        String actualPageAfterLogin = webDriver.getCurrentUrl();
//        assertEquals(actualPageAfterLogin, INBOX);
//
//        WebElement loggedInAccountButton = webDriver.findElement(By.cssSelector(".gb_b.gb_db.gb_R"));
//        assertTrue(loggedInAccountButton.isDisplayed());
