package com.epam.cdp.base.runners;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Runs tests from testng.xml
 */
public class XmlTestRunner {
    public static void main(String[] args) {

        TestNG testng = new TestNG();

        XmlSuite suite = new XmlSuite();
        suite.setSuiteFiles(Arrays.asList("./src/test/resources/testng.xml"));

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        testng.setXmlSuites(suites);
        testng.run();
    }


}
