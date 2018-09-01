package com.kunitskaya.selenium.pages.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ComposeEmailPopup {
    public static final String ALERT_TEXT = "Send this message without a subject or text in the body?";

    public static final By TO_FIELD = By.xpath("//textarea[@name='to']");
    public static final By SEND_BUTTON = By.xpath("//div[text()='Send']");
    public static final By SENT_FOLDER = By.linkText("Sent Mail");

    public static void enterTo(String to) {
        $(TO_FIELD).setValue(to);
    }

    public static void clickSendButton() {
        $(SEND_BUTTON).click();
    }
}
