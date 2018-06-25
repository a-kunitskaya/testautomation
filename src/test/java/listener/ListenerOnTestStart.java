package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import suite.BaseTest;

/**
 * Override onTestStart()
 */
public class ListenerOnTestStart extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Listener: starting test " + iTestResult.getMethod().getMethodName());
    }


    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }
    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
