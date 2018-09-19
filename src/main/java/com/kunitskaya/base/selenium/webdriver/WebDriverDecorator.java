package com.kunitskaya.base.selenium.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.util.List;
import java.util.Set;

import static com.kunitskaya.logging.TestLogger.LOGGER;

public class WebDriverDecorator implements WebDriver, JavascriptExecutor, HasInputDevices, TakesScreenshot {
    protected WebDriver webDriver;

    public WebDriverDecorator(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void get(String s) {
        LOGGER.info("Getting URL " + s);
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        LOGGER.info("Getting current URL");
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        LOGGER.info("Getting page title");
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        LOGGER.info("Finding elements " + by.toString());
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        LOGGER.info("Finding element " + by.toString());
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        LOGGER.info("Closing the browser...");
        webDriver.close();
    }

    @Override
    public void quit() {
        LOGGER.info("Qutting webdriver... ");
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
        LOGGER.info("Switching now...");
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

    @Override
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor)webDriver).executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor)webDriver).executeAsyncScript(s, objects);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices)webDriver).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices)webDriver).getMouse();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        LOGGER.info("Taking screenshot... ");
        return ((TakesScreenshot) webDriver).getScreenshotAs(outputType);
    }
}