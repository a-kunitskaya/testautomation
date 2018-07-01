package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {

    @BeforeMethod
    public void verifyLogIn() {
    }

    @Test(dependsOnMethods = "com.kunitskaya.tests.LogInTest.logIn")
    public void logOut() {

        System.out.println(webDriver.getCurrentUrl());

        webDriver.findElement(By.xpath("//span[@class='gb_db gbii']")).click();
        webDriver.findElement(By.id("gb_71")).click();
    }
}
