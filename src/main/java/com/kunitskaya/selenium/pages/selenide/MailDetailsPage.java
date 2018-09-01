package com.kunitskaya.selenium.pages.selenide;

import org.openqa.selenium.By;

public class MailDetailsPage {
    public static final By TO_VALUE = By.xpath("//span[@dir='ltr' and @class='g2']");
    public static final By SUBJECT_VALUE = By.cssSelector("h2.hP");
    public static final By BODY_VALUE = By.xpath("//div/div[@dir='ltr']");

    public static final String TO_ATTRIBUTE = "email";
}
