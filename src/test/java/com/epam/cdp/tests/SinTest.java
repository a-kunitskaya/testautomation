package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SinTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("sinProvider")
    public void validateSinTest(double a){
        double expectedResult = Math.sin(a);
        double actualResult =  calculator.sin(a);
        assertEquals(expectedResult, actualResult);
    }

    static Stream<Double> sinProvider(){
        return Stream.of(1.0, 2.5, -5.3, -1.0, 0.0);
    }


}
