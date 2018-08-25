package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.operations.pf.EmailOperations;
import com.kunitskaya.business.operations.pf.NavigationOperations;
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
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() {
        Email expectedEmail = TestDataProvider.getEmailWithoutBody();
        EmailOperations.sendEmail(expectedEmail.getReceiver());

        assertTrue(browser.isAlertDisplayed());

        browser.acceptAlert();
        NavigationOperations.goToSentMailFolder();

        String noSubject = "(no subject)";
        expectedEmail.setSubject(noSubject);

        NavigationOperations.goToEmail(expectedEmail);
        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }
}
