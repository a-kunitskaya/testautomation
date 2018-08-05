package com.kunitskaya.pages;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SendInvalidEmailTest extends BaseTest {
    @BeforeClass
    public void logIn() {
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() {
        MailPage mailPage = new MailPage();
        Email email = TestDataProvider.getEmail();

        String noSubject = "(no subject)";
        String to = email.getReceiver();

        //create email without subject, body and send it
        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton()
                                                      .fillInToField(to)
                                                      .sendEmailWithHotKeys();

        assertTrue(composeEmailPopup.isAlertDisplayed());

        composeEmailPopup.acceptAlert();

        //verify that email is in the sent folder
        String sentMailContent = mailPage.clickSentMailLink()
                                         .openEmailWithSubject(noSubject)
                                         .getMailContent();

        assertEquals(sentMailContent, noSubject + to);
    }
}
