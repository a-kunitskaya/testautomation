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

//
//        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
//        webDriver.findElement(By.id("identifierNext")).click();
//
//        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
//        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");
//
//        assertEquals(profileIdentifierValue, USERNAME);
//
//
//        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
//
//        webDriver.findElement(By.id("passwordNext")).click();
//
//
//        String actualPageAfterLogin = webDriver.getCurrentUrl();
//        assertEquals(actualPageAfterLogin, INBOX);
//
//        WebElement loggedInAccountButton = webDriver.findElement(By.cssSelector(".gb_b.gb_db.gb_R"));
//        assertTrue(loggedInAccountButton.isDisplayed());
