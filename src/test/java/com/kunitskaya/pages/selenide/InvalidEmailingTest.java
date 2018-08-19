package com.kunitskaya.pages.selenide;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.operations.selenide.EmailOperations;
import com.kunitskaya.business.operations.selenide.NavigationOperations;
import com.kunitskaya.business.operations.selenide.UserOperations;
import com.kunitskaya.test.TestDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.kunitskaya.pages.selenide.ComposeEmailPopup.ALERT_TEXT;
import static com.kunitskaya.pages.selenide.MailDetailsPage.*;
import static com.kunitskaya.pages.selenide.MailListingPage.DEFAULT_SUBJECT;
import static com.kunitskaya.pages.selenide.MailPage.ACCOUNT_ICON;


public class InvalidEmailingTest extends BaseTest {
    @Test
    public void logIn() {
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(user);

        //assert that account icon is displayed
        $(ACCOUNT_ICON).shouldBe(visible);
    }

    @Test(dependsOnMethods = "logIn")
    public void sendInvalidEmail() throws InterruptedException {
        Email email = TestDataProvider.getDefaultEmail();
        EmailOperations.sendEmail(email.getReceiver());
        $(confirm(ALERT_TEXT));

        NavigationOperations.goToSentFolder();

        //find email by subject, assert it's visible in the Sent folder
        $(byText(DEFAULT_SUBJECT)).shouldBe(visible);

        EmailOperations.openEmail(DEFAULT_SUBJECT);

        //assert the opened email is the one I actually sent
        $(TO_VALUE).shouldHave(attribute(TO_ATTRIBUTE, email.getReceiver()));
        $(SUBJECT_VALUE).shouldHave(text(DEFAULT_SUBJECT));
        $(BODY_VALUE).shouldHave(text(StringUtils.EMPTY));
    }
}
