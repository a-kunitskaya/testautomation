package com.kunitskaya.pf;

import com.kunitskaya.base.waits.ExplicitWait;
import com.kunitskaya.base.waits.ImplicitWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageFactory {
    private static final int DEFAULT_TIMEOUT = 20;
    protected WebDriver webDriver;

    protected AbstractPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void waitForElementVisibility(WebElement webElement) {
        new WebDriverWait(webDriver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForPageLoadComplete() {
        ExplicitWait.waitForPageLoadComplete(webDriver);
    }

    protected void waitForAjaxExecution() {
        ImplicitWait.waitForAjaxExecution(webDriver, DEFAULT_TIMEOUT);
    }

    protected void waitForElementToBeClickable(WebElement webElement) {
        new WebDriverWait(webDriver, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));

    }

    public String getCurrentPageTitle() {
        waitForPageLoadComplete();
        return webDriver.getTitle();
    }

    public AbstractPageFactory switchToLastOpenedWindow() {
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
        return this;
    }

    public String getCurrentWindowHandle() {
        return webDriver.getWindowHandle();
    }

    public AbstractPageFactory switchToWindowHandle(String windowHandle) {
        webDriver.switchTo().window(windowHandle);
        return this;
    }
}
