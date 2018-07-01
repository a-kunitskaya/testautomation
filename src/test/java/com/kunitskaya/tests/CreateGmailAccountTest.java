package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import com.kunitskaya.base.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.HOME_PAGE;
import static org.testng.Assert.assertEquals;

/**
 * Aimed to create a gmail account with a script, but google sends an activation code to a phone number,
 * so the script stops on the "Verify your phone number" page
 *
 * Using the following account in scripts instead:
 * username: aktestautomation@gmail.com
 * password: 123456Ak
 */
public class CreateGmailAccountTest extends BaseTest {

    StringUtil stringUtil = new StringUtil();


    @Test
    public void createAccount() {
        webDriver.get(HOME_PAGE);
        webDriver.findElement(By.id("identifierId")).sendKeys("aktest");
        webDriver.findElement(By.xpath("//div[@jsname='YU8Bzc']")).click();
        webDriver.findElement(By.name("firstName")).sendKeys("ak");
        webDriver.findElement(By.id("lastName")).sendKeys("test");


        //get the suggested username from the field
        WebElement username = webDriver.findElement(By.id("username"));
        String usernameValue = stringUtil.getRandomString(7);
        username.sendKeys(usernameValue);

        WebElement password = webDriver.findElement(By.name("Passwd"));

        String passwordValue = "123456Ak";
        password.sendKeys(passwordValue);

        WebElement confirmPassword = webDriver.findElement(By.name("ConfirmPasswd"));
        confirmPassword.sendKeys(passwordValue);

        //verify that the entered "password" = entered "confirm password"
        assertEquals(password.getAttribute("data-initial-value"), confirmPassword.getAttribute("data-initial-value"));

        webDriver.findElement(By.id("accountDetailsNext")).click();
    }
}
