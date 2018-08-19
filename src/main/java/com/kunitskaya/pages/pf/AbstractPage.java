package com.kunitskaya.pages.pf;

import com.kunitskaya.base.webdriver.WebDriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage {
    WebDriver webDriver;

    public AbstractPage() {
        this.webDriver = WebDriverProvider.getInstance();
        waitForPageLoadComplete(webDriver);
        PageFactory.initElements(webDriver, this);

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
}
