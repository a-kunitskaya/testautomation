package suite;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Test validateIsPositive() should pass 4 times, 1 time should fail (data: {123, false})
 * @Test validateDependencyOnValidateIsPositive() depends on validateIsPositive(),
 * so it won't be executed
 */
public class IsPositiveTest {
    Calculator calculator = new Calculator();

    @Test(dataProvider = "isPositiveDataProvider")
    public void validateIsPositive(long a, boolean expectedResult) {
        boolean actualResult = calculator.isPositive(a);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "isPositiveDataProvider", dependsOnMethods = "validateIsPositive")
    public void validateDependencyOnValidateIsPositive(long a, boolean expectedResult){
        System.out.println("The test should be executed only when validateIsPositive() is success");
    }

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
