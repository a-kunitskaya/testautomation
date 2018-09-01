package com.kunitskaya.selenium.pages.selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MailListingPage {
    public static final String DEFAULT_SUBJECT = "(no subject)";

    public static void openEmailBySubject(String subject) {
        $(byText(subject)).click();
    }
}
