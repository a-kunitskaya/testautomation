package com.epam.cdp.base;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * Issue with JUnitPlatform in JUnit 5
 * https://github.com/junit-team/junit5/issues/1334
 * tests are seen but not executed
 * the same for @SelectClasses
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("com.epam.cdp.tests")
public class SuiteRunner {
}
