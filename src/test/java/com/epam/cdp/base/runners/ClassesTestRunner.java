package com.epam.cdp.base.runners;

import org.testng.TestNG;
import com.epam.cdp.tests.MultTest;
import com.epam.cdp.tests.SumTest;

/**
 * Runs tests from specified classes
 */
public class ClassesTestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{MultTest.class, SumTest.class});
        testng.run();
    }
}