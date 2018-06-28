package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Tag-s are used (same as @Categories in junit 4)
 * Junit 5 assertions are used
 * The test fails with @ValueSource -4 (it's a bug)
 * @Rule and @ClassRule no longer exist in junit5, so now it's @ExtendWith
 */
public class SqrtTest extends BaseTest{

    @ParameterizedTest
    @Tag("pass")
    @ValueSource(doubles = {0, 1, 4, 4.9, 10, -4, Math.PI})
    void validateSqrt(double a) {
        double expectedResult = Math.sqrt(a);
        double actualResult = calculator.sqrt(a);
        assertEquals(expectedResult, actualResult);
    }
}
