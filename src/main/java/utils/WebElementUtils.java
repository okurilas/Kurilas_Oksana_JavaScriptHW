package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementUtils {

    public WebElement $(By locator, WebDriver driver) {
        return driver.findElement(locator);
    }
}
