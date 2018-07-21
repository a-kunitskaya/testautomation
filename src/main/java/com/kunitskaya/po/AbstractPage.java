package com.kunitskaya.po;

import com.kunitskaya.base.Browser;
import org.openqa.selenium.WebDriver;

public class AbstractPage extends Browser {
    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        super(webDriver);
    }
}
