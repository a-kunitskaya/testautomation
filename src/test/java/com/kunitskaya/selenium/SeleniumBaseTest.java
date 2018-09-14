package com.kunitskaya.selenium;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.base.test.Folders;
import com.kunitskaya.selenium.business.objects.user.GmailUserCreator;
import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.AbstractPage;
import com.kunitskaya.selenium.pages.pf.MailPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

public class SeleniumBaseTest {
    protected Browser browser;
    protected User user = new GmailUserCreator().createUser();
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        browser = Browser.getInstance();
        browser.clearCookies();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void makeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TEST_LOGGER.error("Failed test: " + result.getMethod().getMethodName());
            new AbstractPage().takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() {
        if (new MailPage().isAccountIconVisible()) {
            NavigationOperations.goToFolder(Folders.SENT);
            UserOperations.deleteAllEmails();
        }
        browser.quit();
        softAssert.assertAll();
    }
}
