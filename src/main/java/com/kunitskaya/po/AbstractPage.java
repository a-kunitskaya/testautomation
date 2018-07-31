package com.kunitskaya.po;

import com.kunitskaya.base.WebDriverProvider;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage {
    WebDriver webDriver;

    public AbstractPage() {
        try {
            this.webDriver = WebDriverProvider.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitForPageLoadComplete(webDriver);
    }
}
