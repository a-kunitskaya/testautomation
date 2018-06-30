package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    protected WebDriver webDriver = new ChromeDriver();

    @AfterMethod
    public void tearDown(){
        webDriver.close();
    }
}