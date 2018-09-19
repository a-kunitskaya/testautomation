package com.kunitskaya.base.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kunitskaya.base.utils.Screenshoter.SCREENSHOT_NAME;
import static com.kunitskaya.logging.TestLogger.LOGGER;

/**
 * Created by Alexandra Kunitskaya
 */
public class AllureAttachmentsUtil {
    /**
     * Adds screenshot to Allure report
     *
     * @param attachment - file to attach
     */
    public static void addScreenshotToReport(File attachment) {
        Path content = Paths.get(attachment.getAbsolutePath());
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(SCREENSHOT_NAME, is);
        } catch (IOException e) {
            LOGGER.error("Could not add attachment to Allure report" + e.getMessage());
        }
    }
}
