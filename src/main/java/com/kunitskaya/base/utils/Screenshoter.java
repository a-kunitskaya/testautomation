package com.kunitskaya.base.utils;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

/**
 * Created by Alexandra Kunitskaya
 */
public class Screenshoter
{
	public static final String SCREENSHOT_NAME = "Screenshot_" + System.nanoTime();

	/**
	 * Takes scheenshot and puts it in ./target/screenshots/
	 *
	 * @param webDriver - the used webDriver
	 * @return screenshot file
	 */
	public static File takeScreenshot(WebDriver webDriver) {
		File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		String screenshotName = SCREENSHOT_NAME;
		String path = "./target/screenshots/" + SCREENSHOT_NAME + ".png";
		try {
			FileUtils.copyFile(screenshot, new File(path));
			TEST_LOGGER.info("Saved screenshot: " + screenshotName);
		} catch (IOException e) {
			TEST_LOGGER.error("Failed to take screenshot: " + e.getMessage());
		}
		return screenshot;
	}
}
