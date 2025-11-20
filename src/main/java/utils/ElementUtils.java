package utils;

import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.stream.Collectors;
import java.time.Duration;
import java.util.*;

public abstract class ElementUtils {
    public static JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    public static By getDynamicXpath(By locatorTemplate, Object... dynamicValues) {
        String locatorStr = locatorTemplate.toString();
        String locator = locatorStr.substring(locatorStr.indexOf(": ") + 1).trim();
        locator = String.format(locator, dynamicValues);
        if (locatorStr.startsWith("By.xpath:")) {
            return By.xpath(locator);
        } else if (locatorStr.startsWith("By.id:")) {
            return By.id(locator);
        } else if (locatorStr.startsWith("By.name:")) {
            return By.name(locator);
        } else if (locatorStr.startsWith("By.linkText:")) {
            return By.linkText(locator);
        } else if (locatorStr.startsWith("By.tagName:")) {   // ✅ added support for tagName
            return By.tagName(locator);
        } else {
            throw new IllegalArgumentException("Unsupported locator type: " + locatorStr);
        }
    }

    public static List<WebElement> getListOfElements(By locator, String dynamicValue) {
        By finalLocator = getDynamicXpath(locator, dynamicValue);
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(finalLocator));
            List<WebElement> elements = driver().findElements(finalLocator);

            // Optional: Filter visible ones only
            elements = elements.stream()
                    .filter(WebElement::isDisplayed)
                    .collect(Collectors.toList());

            System.out.println("Elements found: " + elements.size() + " using locator: " + finalLocator);
            return elements;
        } catch (TimeoutException e) {
            System.out.println("No elements found for locator: " + finalLocator);
            return Collections.emptyList();
        }
    }

    public static void normalClickEle(By ele, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(timeout));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));

        try {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            element = wait.until(ExpectedConditions.elementToBeClickable(ele));
            element.click();
        }
    }

    public static void mouseHover(By ele) {
        WebElement element = driver().findElement(ele);
        new Actions(driver())
                .moveToElement(element).build()
                .perform();
    }

    public static void scrollToEle(By footer) {
        WebElement element = DriverManager.getDriver().findElement(footer);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToFooterToEnd() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    public abstract String getCurrentURL();

}
