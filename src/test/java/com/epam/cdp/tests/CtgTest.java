package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CtgTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.epam.cdp.tests.factories.CtgFactory#ctgProvider")
    void validateCos(double a){
        double expectedResult = Math.cos(a);
        double actualResult = calculator.cos(a);
        assertEquals(expectedResult, actualResult);

    }
}
