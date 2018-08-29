package com.kunitskaya.business.operations.selenide;

import static com.kunitskaya.pages.selenide.ComposeEmailPopup.clickSendButton;
import static com.kunitskaya.pages.selenide.ComposeEmailPopup.enterTo;
import static com.kunitskaya.pages.selenide.MailListingPage.openEmailBySubject;
import static com.kunitskaya.pages.selenide.MailPage.clickComposeButton;

public class EmailOperations {
    /**
     * Creates and sends email with filled in "to"
     *
     * @param to - email receiver
     */
    public static void sendEmail(String to) {
        clickComposeButton();
        enterTo(to);
        clickSendButton();
    }

    /**
     * Opens email with the specified subject
     *
     * @param subject - email subject
     */
    public static void openEmail(String subject) {
        openEmailBySubject(subject);
    }
}
