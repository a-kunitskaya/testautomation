package com.epam.cdp.tests.factories;

import com.epam.cdp.tests.DivTest;
import org.testng.annotations.Factory;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class DivTestFactory {
    @Factory
    public Object[] createDivInstance() {
        return new Object[]{
                new DivTest(10.4, 2, 5.2),
                new DivTest(-10, 2, -5),
                new DivTest(532, 0, Infinity)};
    }
}
