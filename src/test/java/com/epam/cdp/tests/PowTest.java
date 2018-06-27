package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The test is invoked 6 times in 2 parallel threads
 */
public class PowTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        System.out.println("Set up beforeMethod in PowTest");
    }

    @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 5000, dataProvider = "powDataProvider", priority = 0, description = "Validate pow with doubles")
    public void testDoublePow(double a, double b, double expectedResult) throws InterruptedException {
        double actualResult = calculator.pow(a, b);
        Assert.assertEquals(actualResult, expectedResult);

        System.out.println("Time executed (parallel methods): " + new Date(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(1);
    }

    @DataProvider(name = "powDataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {2, 3, 8},
                {5.5, 3, 166.375},
                {7, -1, (double) 1 / 7}};
    }
}
