package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * In this class all tests should pass
 * No parameters
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

    @Test(priority = 5, description = "Validate summary with doubles")
    public void validateCorrectDoubleSum() {
        int a = 5;
        double b = 6.5;
        double expectedSum = 11.5;
        double actualSum = calculator.sum(a, b);
        Assert.assertEquals(actualSum, expectedSum);
    }

    @Test(groups = {"notBroken"}, description = "Validate summary with longs, include the test group")
    public void validateCorrectLongSum() {
        long a = 6000L;
        long b = 20000L;
        long expectedSum = 26000L;
        long actualSum = calculator.sum(a, b);
        Assert.assertEquals(actualSum, expectedSum);
    }

    @Test(groups = {"broken"}, description = "Exclude the test group")
    public void brokenTest() {
        System.out.println("The test is broken. Should be excluded by group");
    }

    @AfterClass
    public void tearDownAfterSumTestClass() {
        System.out.println("Tear down after com.epam.cdp.tests.SumTest class");
    }
}
