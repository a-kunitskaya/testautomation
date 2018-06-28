package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A Hamcrest assert is used,
 * Jupiter parameters are used,
 * Group junit5 assertions are used
 */
public class isNegativeTest extends BaseTest {

    @BeforeEach
    public void setUpBeforeTests() {
        System.out.println("Set up before @Test in " + this.getClass());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, -43654693})
    void validateIsNegativeTrueLong(long a) {
        boolean expectedResult = true;
        boolean actualResult = calculator.isNegative(a);
        assertThat(actualResult, is(equalTo(expectedResult)));
        System.out.println("data: " + a);
    }

    @Test
    void validateIsNegativeTrueGroupAssertions() {
        long a = -1;
        boolean expectedResult = true;
        boolean actualResult = calculator.isNegative(a);
        assertAll("actualResult",
                () -> assertEquals(expectedResult, actualResult, "Validate actual and expected results are equal"),
                () -> assertEquals(true, actualResult)
        );
    }
}
