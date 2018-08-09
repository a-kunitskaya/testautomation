package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.operations.EmailOperations;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidEmailTest extends BaseTest {
    @BeforeClass
    public void logIn() {
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() {
        MailPage mailPage = new MailPage();
        Email email = TestDataProvider.getEmail();
        String to = email.getReceiver();

        //create email without subject, body and send it
        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton()
                                                      .fillInToField(to)
                                                      .sendEmailWithHotKeys();

        assertTrue(composeEmailPopup.isAlertDisplayed());
        composeEmailPopup.acceptAlert();

        String noSubject = "(no subject)";
        email.setSubject(noSubject);

        String sentMailContent = EmailOperations.getSentMailPartialContent(email);
        assertEquals(sentMailContent, noSubject + to);
    }
}
