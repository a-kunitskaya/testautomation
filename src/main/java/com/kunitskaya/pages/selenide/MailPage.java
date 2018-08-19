package com.kunitskaya.pages.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.kunitskaya.pages.selenide.ComposeEmailPopup.SENT_FOLDER;

public class MailPage {
    public static final By ACCOUNT_ICON = By.cssSelector(".gb_b.gb_eb.gb_R");
    public static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");

    public static void clickComposeButton() {
        $(COMPOSE_BUTTON).click();
    }

    public static void clickSentFolderLink() {
        $(SENT_FOLDER).click();
    }
}