package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.business.operations.pf.EmailOperations;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.test.selenium.Folders;
import com.kunitskaya.test.selenium.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidEmailTestSelenium extends SeleniumBaseTest {

    @BeforeClass
    public void logIn() {
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() {
        Email expectedEmail = TestDataProvider.getEmailWithoutSubjectAndBody();
        EmailOperations.createAndSendEmail(expectedEmail);
        assertTrue(browser.isAlertDisplayed());

        browser.acceptAlert();

        String noSubject = "(no subject)";
        expectedEmail.setSubject(noSubject);

        NavigationOperations.goToSentMailFolder();
        NavigationOperations.goToEmail(expectedEmail);
        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }
}
