package com.kunitskaya.base.selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.Set;

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

public class WebDriverDecorator implements WebDriver {
    protected WebDriver webDriver;

    public WebDriverDecorator(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void get(String s) {
        TEST_LOGGER.info("Getting URL " + s);
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
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
        return webDriver.getPageSource();
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
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        Reporter.log("Switching now...");
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }
}
