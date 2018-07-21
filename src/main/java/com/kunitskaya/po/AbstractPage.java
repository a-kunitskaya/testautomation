package com.kunitskaya.po;

import com.kunitskaya.base.Browser;
import org.openqa.selenium.WebDriver;

public class AbstractPage extends Browser {

    private static final int DEFAULT_TIMEOUT = 20;
    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        super(webDriver);
    }
}
