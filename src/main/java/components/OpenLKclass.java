package components;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenLKclass {

    public void openLK(WebDriverWait wait, Logger logger){

        By username = By.cssSelector("div.header2-menu__item-wrapper.header2-menu__item-wrapper__username");
        By lk = By.xpath("//a[contains(text(),'Личный кабинет')]");
        By lkTitle = By.cssSelector("h1.title__text");

        logger.info("Открыть Личный кабинет");

        wait.until(ExpectedConditions.elementToBeClickable(username))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(lk))
                .click();
        String text = wait.until(ExpectedConditions.presenceOfElementLocated(lkTitle))
                .getText();
        Assert.assertTrue(text.contains("Личный кабинет"));

    }
}
