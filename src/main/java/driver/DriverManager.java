package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        if (tlDriver.get() != null) {
            return; // prevent duplicate browsers
        }

        tlDriver.set(switch (browser.toLowerCase())
        {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(new FirefoxOptions());
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(new ChromeOptions());
            }
        });
        tlDriver.get().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}




////package driver;
////
////import org.openqa.selenium.WebDriver;
////
////public class DriverManager {
////
////
////    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
////
////    public static WebDriver getDriver() {
////        return driver.get();
////    }
////
////    public static void setDriver(WebDriver driverInstance) {
////        driver.set(driverInstance);
////    }
////
////    public static void unload() {
////        driver.remove();
////    }
////
////}
//package driver;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//public class DriverManager {
//
//    private static DriverManager instance = null;
//
//    // ThreadLocal WebDriver
//    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    private DriverManager() {}   // Singleton constructor
//
//    public static DriverManager getInstance() {
//        if (instance == null) {
//            synchronized (DriverManager.class) {
//                if (instance == null) {
//                    instance = new DriverManager();
//                }
//            }
//        }
//        return instance;
//    }
//
//    // ---------------------------
//    //   Driver Factory + Manager
//    // ---------------------------
//
//    public void initDriver(String browser) {
//
//        if (browser == null) {
//            browser = "chrome";
//        }
//
//        switch (browser.toLowerCase()) {
//            case "firefox":
//                FirefoxOptions ffOptions = new FirefoxOptions();
//                tlDriver.set(new FirefoxDriver(ffOptions));
//                break;
//
//            default:
//                ChromeOptions chromeOptions = new ChromeOptions();
//                tlDriver.set(new ChromeDriver(chromeOptions));
//                break;
//        }
//
//        getDriver().manage().window().maximize();
//    }
//
//    public WebDriver getDriver() {
//        return tlDriver.get();
//    }
//
//    public void unloadDriver() {
//        tlDriver.remove();
//    }
//}
