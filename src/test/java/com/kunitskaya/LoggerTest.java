package com.kunitskaya;

import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

/**
 * Created by Alexandra Kunitskaya
 */
public class LoggerTest
{
	@Test
	public void loggerTest(){
		TEST_LOGGER.error("error");
		TEST_LOGGER.fatal("fatal");


		WebDriverProvider.getInstance().get("https://www.google.com");
	}
}
