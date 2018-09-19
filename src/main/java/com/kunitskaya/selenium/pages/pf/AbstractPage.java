package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.base.selenium.webdriver.WebDriverDecorator;
import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage {
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
}
