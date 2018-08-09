package com.kunitskaya.pages.selenide;

import com.codeborne.selenide.Configuration;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.objects.User;
import com.kunitskaya.test.TestDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.kunitskaya.pages.pf.LoginPage.LOGIN_PAGE_URL;
import static com.kunitskaya.pages.selenide.ComposeEmailPopup.*;
import static com.kunitskaya.pages.selenide.LoginPage.*;
import static com.kunitskaya.pages.selenide.MailDetailsPage.*;
import static com.kunitskaya.pages.selenide.MailListingPage.DEFAULT_SUBJECT;
import static com.kunitskaya.pages.selenide.MailListingPage.openEmailBySubject;
import static com.kunitskaya.pages.selenide.MailPage.*;


public class InvalidEmailingTest {

    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    public void logIn() {
        User user = TestDataProvider.getUser();

        open(LOGIN_PAGE_URL);
        enterUsername(user.getUsername());

        //assert that the entered username == user username
        $(USERNAME_VALUE).shouldHave(text(user.getUsername()));

        enterPassword(user.getPassword());

        //assert that account icon is displayed
        $(ACCOUNT_ICON).shouldBe(visible);
    }

    @Test(dependsOnMethods = "logIn")
    public void sendInvalidEmail() {
        Email email = TestDataProvider.getEmail();

        clickComposeButton();
        enterTo(email.getReceiver());
        clickSendButton();

        //find alert by text, accept it
        $(confirm(ALERT_TEXT));

        clickSentFolderLink();

        //find email by subject, assert it's visible in the Sent folder
        $(byText(DEFAULT_SUBJECT)).shouldBe(visible);

        openEmailBySubject(DEFAULT_SUBJECT);

        //assert the opened email is the one I actually sent
        $(TO_VALUE).shouldHave(attribute(TO_ATTRIBUTE, email.getReceiver()));
        $(SUBJECT_VALUE).shouldHave(text(DEFAULT_SUBJECT));
        $(BODY_VALUE).shouldHave(text(StringUtils.EMPTY));
    }
}
