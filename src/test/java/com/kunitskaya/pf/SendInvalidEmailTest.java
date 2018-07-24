package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.Email;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SendInvalidEmailTest extends BaseTest {
    @BeforeClass
    public void logIn() {
        LoginPage loginPage = new LoginPage();
        loginPage.open()
                 .fillInUsername(user.getUsername())
                 .clickUsernameNextButton()
                 .fillInPassword(user.getPassword())
                 .clickPasswordNextButton();

    }

    @Test
    public void sendInvalidEmail() {
        MailPage mailPage = new MailPage();
        Email email = TestDataProvider.getEmail();

        String noSubject = "(no subject)";
        String to = email.getReceiver();

        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton();

        //create email without subject, body and send it
        composeEmailPopup.fillInToField(to)
                         .sendEmailWithHotkeys();

        assertTrue(composeEmailPopup.isAlertDisplayed());
        composeEmailPopup.acceptAlert();

        //verify that email is in the sent folder
        SentMailPage sentMailPage = mailPage.clickSentMailLink();
        sentMailPage.openSentMailWithSubject(noSubject);
        String sentMailContent = sentMailPage.getSentMailContent(noSubject);

        assertEquals(sentMailContent, noSubject + to);
    }
}
