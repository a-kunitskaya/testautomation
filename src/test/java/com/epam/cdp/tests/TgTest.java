package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TgTest extends BaseTest{
    @Parameters("a")
    @Test
    public void validateTgWithOptionalParameter(@Optional("2.5") double a){
        double expectedResult = Math.tan(a);
        double actualResult = calculator.tg(a);
        assertEquals(expectedResult, actualResult);
    }
}
