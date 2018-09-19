package com.kunitskaya.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogger {
    public static final Logger ROOT_LOGGER = LogManager.getLogger(TestLogger.class);
    public static final Logger WS_LOGGER = LogManager.getLogger("ws");
    public static final Logger TEST_LOGGER = LogManager.getLogger("test");

    public static void error(String message) {
        ROOT_LOGGER.error(message);
    }

    public static void info(String message) {
        ROOT_LOGGER.info(message);
    }

    public static void error(String message, Throwable throwable) {
        ROOT_LOGGER.error(message, throwable);
    }

    public static void debug(String message) {
        ROOT_LOGGER.debug(message);
    }

    public static void warn(String message) {
        ROOT_LOGGER.warn(message);
    }

    public static void trace(String message) {
        ROOT_LOGGER.trace(message);
    }

    public static void log(String message) {
        ROOT_LOGGER.info(message);
    }

}
