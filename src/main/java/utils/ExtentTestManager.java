package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }
    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }
    public static synchronized ExtentReports getExtent() {
        return ExtentManager.getInstance();
    }
}
