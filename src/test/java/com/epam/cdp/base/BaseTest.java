package com.epam.cdp.base;

import com.epam.tat.module4.Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp(){
        System.out.println("Set up before class");
    }

    public Calculator calculator = new Calculator();

    @AfterAll
    public static void tearDown(){
        System.out.println("Tear down after class");
    }
}
