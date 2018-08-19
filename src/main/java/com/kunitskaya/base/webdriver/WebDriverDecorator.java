package com.kunitskaya.base.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.Set;

public class WebDriverDecorator implements WebDriver {
    protected WebDriver webDriver;

    public WebDriverDecorator(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void get(String s) {
        Reporter.log("Getting URL " + s);
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        Reporter.log("Finding elements " + by.toString());
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        Reporter.log("Finding elements " + by.toString());
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {
        Reporter.log("Closing the browser...");
        webDriver.close();
    }

    @Override
    public void quit() {
        Reporter.log("Qutting webdriver... ");
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        Reporter.log("Switching now...");
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }
}
