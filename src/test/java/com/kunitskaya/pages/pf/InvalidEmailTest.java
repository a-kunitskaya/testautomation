package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.operations.EmailOperations;
import com.kunitskaya.business.operations.NavigaionOperations;
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
        Email email = TestDataProvider.getEmail();
        String to = email.getReceiver();

        EmailOperations.sendEmptyEmail(to);
        assertTrue(new ComposeEmailPopup().isAlertDisplayed());

        UserOperations.acceptAlert();

        String noSubject = "(no subject)";
        email.setSubject(noSubject);

        NavigaionOperations.goToSentMailFolder();

        String sentMailContent = EmailOperations.getMailContent(email);
        assertEquals(sentMailContent, noSubject + to);
    }
}
