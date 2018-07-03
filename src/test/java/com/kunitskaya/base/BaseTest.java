package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver webDriver = WebDriverSingleton.getInstance();

    @BeforeSuite
    public void setUp(){
//        webDriver.manage().window().fullscreen();
//        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void TakeScreenshot(){
    }

    @AfterSuite
        public void tearDown() {
            webDriver.close();
        }
}