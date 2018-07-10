package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.testng.Assert.assertTrue;

/**
 * Aimed to create a gmail account with a script, but google sends an activation code to a phone number,
 * so the script stops on the "Verify your phone number" page
 * Using the following account in scripts instead:
 * username: aktestautomation@gmail.com
 * password: 123456Ak
 */
public class CreateGmailAccountTest extends BaseTest {

    @Test
    public void createAccount() {

        webDriver.get(HOME_PAGE);

        waitForElementVisibility(webDriver, 30, By.cssSelector("div.daaWTb > div > div > content > span"));

        webDriver.findElement(By.cssSelector("div.daaWTb > div > div > content > span")).click();
        webDriver.findElement(By.name("firstName")).sendKeys(NEW_ACCOUNT_FIRST_NAME);
        webDriver.findElement(By.cssSelector("#lastName")).sendKeys(NEW_ACCOUNT_LAST_NAME);

        WebElement username = webDriver.findElement(By.id("username"));
        String usernameValue = randomAlphanumeric(7);
        username.sendKeys(usernameValue);

        WebElement password = webDriver.findElement(By.name("Passwd"));

        password.sendKeys(NEW_ACCOUNT_PASSWORD);

        WebElement confirmPassword = webDriver.findElement(By.name("ConfirmPasswd"));
        confirmPassword.sendKeys(NEW_ACCOUNT_PASSWORD);

        webDriver.findElement(By.id("accountDetailsNext")).click();

        waitForElementVisibility(webDriver, 30, By.id("phoneNumberId"));
        assertTrue(webDriver.findElement(By.id("phoneNumberId")).isDisplayed());
    }
}
