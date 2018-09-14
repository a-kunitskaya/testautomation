package com.kunitskaya.base.selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        TEST_LOGGER.info("Getting current URL");
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        TEST_LOGGER.info("Getting page title");
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        TEST_LOGGER.info("Finding elements " + by.toString());
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        TEST_LOGGER.info("Finding elements " + by.toString());
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        TEST_LOGGER.info("Closing the browser...");
        webDriver.close();
    }

    @Override
    public void quit() {
        TEST_LOGGER.info("Quiting webdriver... ");
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
        TEST_LOGGER.info("Switching now...");
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
