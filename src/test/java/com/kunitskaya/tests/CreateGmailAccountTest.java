package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateGmailAccountTest extends BaseTest {

    @Test
    public void createAccount() {
        webDriver.get("https://www.gmail.com");
        webDriver.findElement(By.id("identifierId")).sendKeys("aktest");
        webDriver.findElement(By.xpath("//div[@jsname='YU8Bzc']")).click();
    }
}
