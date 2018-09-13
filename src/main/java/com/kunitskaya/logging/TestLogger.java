package com.kunitskaya.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogger {
    public static final Logger TEST_LOGGER = LogManager.getLogger(TestLogger.class);

    public static void error(String message) {
        TEST_LOGGER.error(message);
    }

    public static void info(String message) {
        TEST_LOGGER.info(message);
    }

    public static void error(String message, Throwable throwable) {
        TEST_LOGGER.error(message, throwable);
    }

    public static void debug(String message) {
        TEST_LOGGER.debug(message);
    }

    public static void warn(String message) {
        TEST_LOGGER.warn(message);
    }

    public static void trace(String message) {
        TEST_LOGGER.trace(message);
    }

    public static void log(String message) {
        TEST_LOGGER.info(message);
    }

}
