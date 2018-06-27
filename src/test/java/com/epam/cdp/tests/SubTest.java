package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * In this class the test should pass twice
 * Parameters are taken from dataProvider
 */
public class SubTest extends BaseTest {

    @Test(dataProvider = "subDataProvider", priority = 4, description = "Validate substraction with doubles")
    public void validateSub(double a, double b, double expectedResult) {
        double actualResult = calculator.sub(a, b);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "subDataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {100, 10, 90},
                {200, 20, 180}};
    }
}
