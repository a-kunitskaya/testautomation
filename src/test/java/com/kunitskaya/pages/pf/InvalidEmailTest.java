package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.base.webdriver.WebDriverProvider;
import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.operations.pf.EmailOperations;
import com.kunitskaya.business.operations.pf.NavigaionOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InvalidEmailTest extends BaseTest {

    @BeforeClass
    public void logIn() {
        NavigaionOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0001 Gmail: Sending invalid email")
    public void sendInvalidEmail() throws InterruptedException {
        Email expectedEmail = TestDataProvider.getEmailWithoutBody();
        String to = expectedEmail.getReceiver();
        EmailOperations.sendEmptyEmail(to);

        //TODO: fix unexpected alert exception
        try {
            NavigaionOperations.goToSentMailFolder();
        }catch(UnhandledAlertException e){
            Alert alert = WebDriverProvider.getInstance().switchTo().alert();
            alert.accept();
          //  UserOperations.acceptAlert();
            NavigaionOperations.goToSentMailFolder();
        }

        NavigaionOperations.goToSentMailFolder();

        String noSubject = "(no subject)";
        expectedEmail.setSubject(noSubject);

        Email actualEmail = EmailOperations.getActualEmail(expectedEmail, Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }
}
