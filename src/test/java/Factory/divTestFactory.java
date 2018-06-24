package Factory;

import FirstSuite.divTest;
import org.testng.annotations.Factory;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class divTestFactory {
    @Factory
    public Object[] createDivInstance() {
        return new Object[]{
                new divTest(10.4, 2, 5.2),
                new divTest(-10, 2, -5),
                new divTest(532, 0, Infinity)};
    }
}
