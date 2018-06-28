package com.epam.cdp.base;

import com.epam.cdp.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CosTest.class,
        CtgTest.class,
        IsNegativeTest.class,
        SinTest.class,
        SqrtTest.class
})
public class SuiteRunner {
}
