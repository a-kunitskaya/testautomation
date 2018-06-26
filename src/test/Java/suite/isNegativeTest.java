package suite;

import com.epam.tat.module4.Calculator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * A Hamcrest assert is used,
 * Jupiter parameters are used
 */
public class isNegativeTest extends BaseTest {
    Calculator calculator = new Calculator();

    @BeforeEach
    public void setUpBeforeTests(){
        System.out.println("Set up before @Test in " + this.getClass());
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    public void validateIsNegativeTrue(long a){
        boolean expectedResult = true;
        boolean actualResult = calculator.isNegative(a);
        assertThat(actualResult, is(equalTo(expectedResult)));

    }
}
