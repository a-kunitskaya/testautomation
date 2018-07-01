package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static org.testng.Assert.assertEquals;

public class LogInTest extends BaseTest {

    @Test
    public void logIn() throws InterruptedException {
        webDriver.get(HOME_PAGE);
        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
        webDriver.findElement(By.id("identifierNext")).click();

        Thread.sleep(2500);

        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");

        assertEquals(profileIdentifierValue, USERNAME);

        //click the password field  to make it active
        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.click();

        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("passwordNext")).click();

        Thread.sleep(5000);

        String actualPageAfterLogin = webDriver.getCurrentUrl();

        assertEquals(actualPageAfterLogin, INBOX);

    }

    @AfterMethod
    public void TakeScreenshot(){
    }
}
