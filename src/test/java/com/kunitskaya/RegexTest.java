package com.kunitskaya;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegexTest {

    @Test(dataProvider = "regexDataProvider")
    public void validateRegexMatching(String phoneNumber, boolean isMatching) {
        String velcomPattern = "((\\+375)|8)[(\\s\\-]?((44[)\\s\\-]?[1-9])|(029|29)[)\\s\\-]?[1369])\\d{1,2}[\\s\\-]?\\d{2,3}[\\s\\-]?\\d{2,3}";

        if (isMatching) {
            assertTrue(phoneNumber.matches(velcomPattern));
        } else {
            assertFalse(phoneNumber.matches(velcomPattern));
        }
    }

    @DataProvider
    public Object[][] regexDataProvider() {
        return new Object[][]{
                {"+375(29)123 45 67", true},
                {"+375(29)323 45 67", true},
                {"+375(29)623 45 67", true},
                {"+375(29)923 45 67", true},
                {"+375(44)123 45 67", true},
                {"+375 29 12 345 67", true},
                {"+375 29 12 34 567", true},
                {"+375-29-123-45-67", true},
                {"+375291234567", true},
                {"8(029)1234567", true},
                {"8-029-123-45-67", true},
                {"80291234567", true},
                {"+375 29 223 45 67", false},
                {"+375(29)523-45-67", false},
                {"+375297234567", false}

        };
    }
}