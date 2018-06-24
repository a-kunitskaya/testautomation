package FirstSuite;

import com.epam.tat.module4.Calculator;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * The test should pass
 * Parameters are taken from divTestFactory
 */
public class divTest extends FirstSuite.BaseTest {
    private double a;
    private double b;
    private double expectedResult;

    public divTest(double a, double b, double expectedResult) {
        this.a = a;
        this.b = b;
        this.expectedResult = expectedResult;
    }

    Calculator calculator = new Calculator();

    @BeforeClass
    public void setUp() {
        System.out.println("Override setup from baseTest");
    }

    @Test(priority = 1)
    @Description("Validate division with doubles")
    public void validateDoubleDiv() {
        double actualResult = calculator.div(a, b);
        Assert.assertEquals(actualResult, expectedResult);
    }
}
