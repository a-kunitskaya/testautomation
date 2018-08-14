package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.operations.pf.EmailOperations;
import com.kunitskaya.business.operations.pf.NavigaionOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidEmailTest extends BaseTest {

    @BeforeClass
    public void logIn() {
        NavigaionOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() {
        Email email = TestDataProvider.getDefaultGmailEmail();
        String to = email.getReceiver();

        EmailOperations.sendEmptyEmail(to);
        assertTrue(new ComposeEmailPopup().isAlertDisplayed());

        UserOperations.acceptAlert();

        String noSubject = "(no subject)";
        email.setSubject(noSubject);

        NavigaionOperations.goToSentMailFolder();

        String sentMailContent = EmailOperations.getMailContent(email, Folders.SENT);
        assertEquals(sentMailContent, noSubject + to);
    }
}
