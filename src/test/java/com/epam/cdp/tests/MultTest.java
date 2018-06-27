package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The test should pass
 * Parameters are taken from testng.xml or @Optional
 * Test is invoked 2 times in 2 parallel threads with 3 seconds' difference
 */
public class MultTest extends BaseTest {

    @Parameters({"a", "b", "expectedResult"})
    @Test(priority = 2, description = "Validate multiplication with doubles")
    public void validateMult(@Optional("3") double a, @Optional("4") double b, @Optional("12") double expectedResult) {
        double actualResult = calculator.mult(a, b);
        assertEquals(actualResult, expectedResult);
    }
}
