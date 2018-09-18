package com.kunitskaya.selenium;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.base.test.Folders;
import com.kunitskaya.selenium.business.objects.user.GmailUserCreator;
import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.AbstractPage;
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

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;
import static com.kunitskaya.selenium.pages.pf.AbstractPage.SCREENSHOT_NAME;

public class SeleniumBaseTest
{
	protected Browser browser;
	protected User user = new GmailUserCreator().createUser();

	@BeforeClass
	public void setUp()
	{
		browser = Browser.getInstance();
		browser.clearCookies();
	}

	@AfterMethod
	public void makeScreenshotOnFailure(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			TEST_LOGGER.error("Failed test: " + result.getMethod().getMethodName());
			File attachment = new AbstractPage().takeScreenshot();

			Allure.addAttachment("My attachment", "My attachment content");
			Path content = Paths.get(attachment.getAbsolutePath());
			try (InputStream is = Files.newInputStream(content))
			{
				Allure.addAttachment("My attachment", is);
			}
			catch (IOException e)
			{
				TEST_LOGGER.error("Could not add attachment to Allure report");
			}
		}

	}

	@AfterClass
	public void tearDown()
	{
		if (new MailPage().isAccountIconVisible())
		{
			NavigationOperations.goToFolder(Folders.SENT);
			UserOperations.deleteAllEmails();
		}
		browser.quit();
	}
}
