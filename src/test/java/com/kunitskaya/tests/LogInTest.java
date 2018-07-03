package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementExplicitly;
import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;
import static org.testng.Assert.assertEquals;

public class LogInTest extends BaseTest {

    @Test
    public void logIn() throws InterruptedException {
        webDriver.get(HOME_PAGE);
        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
        webDriver.findElement(By.id("identifierNext")).click();
        waitImplicitly(webDriver, 10);
        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");

        assertEquals(profileIdentifierValue, USERNAME);

        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
        waitForElementExplicitly(webDriver, 40, By.id("passwordNext"));
        webDriver.findElement(By.id("passwordNext")).click();

//      waitImplicitly(webDriver, 40); for some reason does not work here
        Thread.sleep(6000);
        String actualPageAfterLogin = webDriver.getCurrentUrl();

        assertEquals(actualPageAfterLogin, INBOX);

    }

}
