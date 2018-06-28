package com.epam.cdp.base;

import com.epam.cdp.tests.*;
import org.junit.runner.JUnitCore;

public class Runner {

    public static void main(String[] args){
        JUnitCore.runClasses(CosTest.class, CtgTest.class,
                IsNegativeTest.class, SinTest.class, SqrtTest.class);
    }

}
