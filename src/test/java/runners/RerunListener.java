package runners;

import org.testng.IExecutionListener;
import org.testng.TestNG;
import java.io.File;

public class RerunListener implements IExecutionListener {

    @Override
    public void onExecutionFinish() {
        var rerunFile = new File("target/rerun.txt");

        System.out.println("Checking rerun file: exists=" + rerunFile.exists() + ", length=" + rerunFile.length());

        if (rerunFile.exists() && rerunFile.length() > 0) {
            System.out.println("Failed scenarios found. Executing FailedRunner...");

            TestNG testng = new TestNG();
            testng.setTestClasses(new Class[]{runners.FailedRunner.class});
            testng.run();

        } else {
            System.out.println("No failed scenarios. Skipping rerun.");
        }
    }

    @Override
    public void onExecutionStart() {
        // optional
    }
}
