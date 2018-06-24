package FirstSuite;

import com.epam.tat.module4.Calculator;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The test should pass
 * Parameters are taken from testng.xml or @Optional
 * Test is invoked 2 times in 2 parallel threads with 3 seconds' difference
 */
public class multTest extends FirstSuite.BaseTest {
    Calculator calculator = new Calculator();

    @Parameters({"a", "b", "expectedResult"})
    @Test(priority = 2)
    @Description("Valifate multiplication with doubles")
    public void validateMult(@Optional("3") double a, @Optional("4") double b, @Optional("12") double expectedResult){
        double actualResult = calculator.mult(a, b);
        Assert.assertEquals(actualResult, expectedResult);
    }


}
