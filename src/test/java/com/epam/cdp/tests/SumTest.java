package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * In this class all tests should pass
 * No parameters
 * Tests are executed according to priority: 1-2-3
 */
public class SumTest extends BaseTest {

    @BeforeClass
    public void setUpBeforeSumTestClass() {
        System.out.println("Set up before com.epam.cdp.tests.SumTest class");
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Set up beforeMethod in SumTest");
    }

    @Test(priority = 3, description = "Validate summary with doubles")
    public void validateCorrectDoubleSum() {
        int a = 5;
        double b = 6.5;
        double expectedSum = 11.5;
        double actualSum = calculator.sum(a, b);
        assertEquals(actualSum, expectedSum);
        System.out.println("Executing test with priority 3");
    }

    @Test(priority = 2, groups = {"notBroken"}, description = "Validate summary with longs, include the test group")
    public void validateCorrectLongSum() {
        long a = 6000L;
        long b = 20000L;
        long expectedSum = 26000L;
        long actualSum = calculator.sum(a, b);
        assertEquals(actualSum, expectedSum);
        System.out.println("Executing test with priority 2");
    }

    @Test(priority = 1, groups = {"broken"}, description = "Exclude the test group")
    public void brokenTest() {
        System.out.println("The test is broken. Should be excluded by group");
        System.out.println("Executing test with priority 1");
    }

    @AfterClass
    public void tearDownAfterSumTestClass() {
        System.out.println("Tear down after com.epam.cdp.tests.SumTest class");
    }
}
