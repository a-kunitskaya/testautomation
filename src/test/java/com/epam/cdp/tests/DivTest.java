package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The test should pass
 * Parameters are taken from DivTestFactory
 */
public class DivTest extends BaseTest {
    private double a;
    private double b;
    private double expectedResult;

    public DivTest(double a, double b, double expectedResult) {
        this.a = a;
        this.b = b;
        this.expectedResult = expectedResult;
    }

    @BeforeClass
    public void setUp() {
        System.out.println("Override setup from baseTest");
    }

    @Test(priority = 1, description = "Validate division with doubles")
    public void validateDoubleDiv() {
        double actualResult = calculator.div(a, b);
        assertEquals(expectedResult, actualResult);
        System.out.println("data: " + a + " " + b);
    }

    @Test(priority = 1, description = "Validate division by zero")
    public void validateDivisionByZero() {
        double a = 10;
        double b = 0;
        double actualResult = calculator.div(a, b);
        assertEquals(new ArithmeticException(), actualResult);
    }
}
