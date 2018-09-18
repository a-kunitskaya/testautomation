package com.kunitskaya.selenium.mobile;

import com.kunitskaya.selenium.pages.pf.AbstractPage;

public class HomePage extends AbstractPage {
    public static final String GOOGLE_HOME_URL = "https://www.google.com/";

    public void open(){
        webDriver.get(GOOGLE_HOME_URL);
    }
}
