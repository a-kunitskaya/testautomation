package com.epam.cdp.tests.dataproviders;

import org.testng.annotations.DataProvider;

public class IsPositiveDataProvider {

    @DataProvider
    public Object[][] isPositiveDataProvider() {
        return new Object[][]{
                {0, false},
                {-10, false},
                {2, true},
                {9223372036854775807L, true},
                {123, false}};


    }
}
