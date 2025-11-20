//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentManager {
//    private static ExtentReports extent;
//
//    public static synchronized ExtentReports getInstance() {
//        if (extent == null) {
//            String reportPath = System.getProperty("user.dir") + "/target/extent-reports/ExtentSpark.html";
//
//            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
//            spark.config().setTheme(Theme.STANDARD);
//            spark.config().setDocumentTitle("Automation Report");
//            spark.config().setReportName("Cucumber + TestNG Execution Report");
//
//            extent = new ExtentReports();
//            extent.attachReporter(spark);
//            extent.setSystemInfo("OS", System.getProperty("os.name"));
//            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
//        }
//        return extent;
//    }
//}

package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Get or create ExtentReports instance
    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/extent-reports/ExtentSpark.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Cucumber + TestNG Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    // Thread-safe ExtentTest getter
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    // Thread-safe ExtentTest setter
    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }
}

