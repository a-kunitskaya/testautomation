package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.Email;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SendInvalidEmailTest extends BaseTest {
    @BeforeClass
    public void logIn() throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.open()
                 .fillInUsername(user.getUsername())
                 .clickUsernameNextButton()
                 .fillInPassword(user.getPassword())
                 .clickPasswordNextButton();

    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() throws IOException {
        MailPage mailPage = new MailPage();
        Email email = TestDataProvider.getEmail();

        String noSubject = "(no subject)";
        String to = email.getReceiver();

        //create email without subject, body and send it
        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton()
                                                      .fillInToField(to)
                                                      .sendEmailWithHotkeys();

        assertTrue(composeEmailPopup.isAlertDisplayed());

        composeEmailPopup.acceptAlert();

        //verify that email is in the sent folder
        String sentMailContent = mailPage.clickSentMailLink()
                                         .openSentMailWithSubject(noSubject)
                                         .getMailContent();

        assertEquals(sentMailContent, noSubject + to);
    }
}
