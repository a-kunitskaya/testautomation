package com.epam.cdp.base;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUpBeforeSuite(){
        System.out.println("Set up beforeSuite");
    }
    @BeforeClass
    public void setUp() {
        System.out.println("Set up from BaseTest, test executed: " + getClass().getName());
    }

    public Calculator calculator = new Calculator();

    @AfterClass
    public void tearDown(){
        System.out.println("Tear down from BaseTest, test finished: " + getClass().getName());
    }

    @AfterSuite
    public void tearDownAfterSuite(){
        System.out.println("Tear down after suite");
    }
}
