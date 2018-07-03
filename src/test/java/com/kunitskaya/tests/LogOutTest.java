package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.INBOX;

public class LogOutTest extends BaseTest {

    @BeforeMethod
    public void verifyLogIn() {
        webDriver.get(INBOX);
    }

    @Test(dependsOnMethods = "com.kunitskaya.tests.LogInTest.logIn")
    public void logOut() {
        webDriver.findElement(By.xpath("//span[@class='gb_db gbii']")).click();
        webDriver.findElement(By.id("gb_71")).click();
    }
}
