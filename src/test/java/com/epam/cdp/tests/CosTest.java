package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest extends BaseTest {


    @ParameterizedTest
    @MethodSource("cosProvider")
    void validateCos(double a){
        double expectedResult = Math.cos(a);
        double actualResult = calculator.cos(a);
        assertEquals(expectedResult, actualResult);

    }

    static Stream<Double> cosProvider(){
        return Stream.of(1.5, 20.0, -3.0);
    }
}
