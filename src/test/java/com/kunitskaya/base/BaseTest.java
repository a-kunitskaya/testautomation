package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    protected WebDriver webDriver = WebDriverSingleton.getInstance();


    @AfterMethod
    public void TakeScreenshot(){
    }

    @AfterSuite
        public void tearDown() {
            webDriver.close();
        }
}