package com.epam.cdp.tests;

import com.epam.cdp.base.BaseTest;
import com.epam.cdp.tests.dataproviders.IsPositiveDataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @Test validateIsPositive() should pass 4 times, 1 time should fail (data: {123, false})
 * @Test validateDependencyOnValidateIsPositive() depends on validateIsPositive(),
 * so it won't be executed
 * @DataProvider is in a separate IsPositiveDataProvider.class
 * validateIsPositive() depends on validateIsPositive()
 * but is executed no matter the validateIsPositive() result
 */
public class IsPositiveTest extends BaseTest {

    @Test(dataProvider = "isPositiveDataProvider", dataProviderClass = IsPositiveDataProvider.class)
    public void validateIsPositive(long a, boolean expectedResult) {
        boolean actualResult = calculator.isPositive(a);
        assertEquals(expectedResult, actualResult);
    }

    @Test(dataProvider = "isPositiveDataProvider", dataProviderClass = IsPositiveDataProvider.class, dependsOnMethods = "validateIsPositive")
    public void validateDependencyOnValidateIsPositive(long a, boolean expectedResult){
        System.out.println("The test should be executed only when validateIsPositive() is success");
    }

    @Test(dependsOnMethods = "validateIsPositive", alwaysRun = true)
    public void alwaysRun(){
        System.out.println("This method is executed even if validateIsPositive() fails");
    }
}
