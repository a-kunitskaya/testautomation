package com.kunitskaya.pf;

import com.kunitskaya.base.Browser;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.PageFactory;

//не наследовать страницы от браузера, драйвер - в конструкцию страниц
//браузер - синглтоном
//quite - exception. чтобы открыть заново браузер, нужно новую сессию. вdriver - null
//wdprowider = добавить метод квит - и там driver.quite(), driver = null(чтобы обнулить драйвер, чтобы потом объект новый создать)
//.property file = test.properties in test/resourses
//там - default timeout, browser, isRemoteDriver

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage extends Browser {

    public AbstractPage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        waitForPageLoadComplete(webDriver);
        return webDriver.getTitle();
    }

    public boolean isAlertDisplayed() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        webDriver.switchTo().alert().accept();
    }
}
