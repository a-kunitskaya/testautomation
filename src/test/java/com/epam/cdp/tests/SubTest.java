package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import com.epam.tat.module4.Calculator;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * In this class the test should pass twice
 * Parameters are taken from dataProvider
 */
public class SubTest extends BaseTest {
    Calculator calculator = new Calculator();

    @Test(dataProvider = "subDataProvider", priority = 4)
    @Description("validate substraction with doubles")
    public void validateSub(double a, double b, double expectedResult) {
        double actualResult = calculator.sub(a, b);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "subDataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {100, 10, 90},
                {200, 20, 180}};
    }
}
