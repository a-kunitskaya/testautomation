package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.base.selenium.webdriver.WebDriverDecorator;
import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForPageLoadComplete;
import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

public class AbstractPage {
    public static final String SCREENSHOT_NAME = "Screenshot_" + System.nanoTime();

    protected WebDriver webDriver;

    public AbstractPage() {
        this.webDriver = WebDriverProvider.getInstance();
        waitForPageLoadComplete(webDriver);
        PageFactory.initElements(webDriver, this);
        webDriver = new WebDriverDecorator(webDriver);
    }

    public String getTitle() {
        waitForPageLoadComplete(webDriver);
        return webDriver.getTitle();
    }


    public void highlightElement(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='5px solid purple'", webElement);
    }

    public void unHighlightElement(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='0px'", webElement);
    }

    public File takeScreenshot() {
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String screenshotName = SCREENSHOT_NAME;
        String path = "./target/screenshots/" + SCREENSHOT_NAME + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(path));
            TEST_LOGGER.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            TEST_LOGGER.error("Failed to take screenshot: " + e.getMessage());
        }
        return screenshot;
    }
}
