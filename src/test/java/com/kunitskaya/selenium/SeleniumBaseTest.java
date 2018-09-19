package com.kunitskaya.selenium;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import com.kunitskaya.base.test.Folders;
import com.kunitskaya.base.utils.AllureAttachmentsUtil;
import com.kunitskaya.base.utils.Screenshoter;
import com.kunitskaya.selenium.business.objects.user.GmailUserCreator;
import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.MailPage;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kunitskaya.base.utils.Screenshoter.SCREENSHOT_NAME;
import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;


public class SeleniumBaseTest
{
	protected Browser browser;
	protected User user = new GmailUserCreator().createUser();

	@BeforeClass
	public void setUp() {
		browser = Browser.getInstance();
		browser.clearCookies();
	}

	@AfterMethod
	public void makeScreenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			TEST_LOGGER.error("Failed test: " + result.getMethod().getMethodName());
			File attachment = Screenshoter.takeScreenshot(WebDriverProvider.getInstance());
			AllureAttachmentsUtil.addScreenshotToReport(attachment);
		}
	}

	@AfterClass
	public void tearDown() {
		if (new MailPage().isAccountIconVisible()) {
			NavigationOperations.goToFolder(Folders.SENT);
			UserOperations.deleteAllEmails();
		}
		browser.quit();
	}
}
