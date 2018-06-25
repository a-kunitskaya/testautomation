package runner;

import org.testng.TestNG;
import suite.BaseTest;
import suite.MultTest;
import suite.SumTest;

/**
 * Runs tests from specified classes
 */
public class ClassesTestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{MultTest.class, SumTest.class});
        testng.run();
    }
}