package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.INBOX;
import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

public class LogOutTest extends BaseTest {

    @BeforeMethod
    public void verifyLogIn() {
        webDriver.get(INBOX);
        waitImplicitly(webDriver, 30);
    }

    @Test
    public void logOut() {
        webDriver.findElement(By.xpath("//span[@class='gb_db gbii']")).click();
        webDriver.findElement(By.id("gb_71")).click();
    }
}
