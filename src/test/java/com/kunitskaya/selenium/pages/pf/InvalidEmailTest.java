package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.business.operations.pf.EmailOperations;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.base.test.Folders;
import com.kunitskaya.base.test.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidEmailTest extends SeleniumBaseTest {

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

        NavigationOperations.goToFolder(Folders.SENT);
        NavigationOperations.goToEmail(expectedEmail);
        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }
}
