package com.epam.cdp.tests.factories;

import com.epam.cdp.tests.DivTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class DivTestFactory {
    @Factory(dataProvider = "divFactoryDataProvider")
    public Object[] createDivInstance(double a, double b, double expectedResult) {
        return new Object[]{
                new DivTest(a, b, expectedResult)
        };
    }

    @DataProvider(name = "divFactoryDataProvider")
    public Object[][] createDivData() {
        return new Object[][]{
                {10.4, 2, 5.2},
                {-10, 2, -5},
                {10, -2, -5},
                {20, 4, 5}
        };
    }
}

