package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class SinTest extends BaseTest {

    @Parameterized.Parameter
    public double a;
    @Parameterized.Parameter
    public double expectedResult;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {1, Math.tan(1)},
                {2.5, Math.tan(2.5)},
                {-5.3, Math.tan(-5.3)}
        };
    }

    @Test
    public void validateSinTest(double a, double expectedResult){
        double actualResult =  calculator.sin(a);
        assertEquals(expectedResult, actualResult);
    }


}
