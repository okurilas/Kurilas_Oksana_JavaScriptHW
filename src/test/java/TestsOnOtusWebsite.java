import components.AuthFormComponent;
import components.OpenLKclass;
import config.IConfigServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestsOnOtusWebsite {

    private Logger logger = LogManager.getLogger(TestsOnOtusWebsite.class);
    private WebDriver driver;
    private IConfigServer cfg = ConfigFactory.create(IConfigServer.class);
    private WebDriverWait wait;
    //private utils.WebElementUtils webElementUtils = new utils.WebElementUtils();
    private AuthFormComponent authFormComponent = new AuthFormComponent();
    private OpenLKclass openLKclass = new OpenLKclass();
    private Actions actions;


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        logger.info("драйвер поднят");
    }

    @After
    public void close(){
        if (driver!=null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void openOtus() throws InterruptedException {
        By otusHeader = By.cssSelector(".header2__logo");
        By aboutMe = By.xpath("//a[contains(text(),'О себе')]");
        By contactOption = By.cssSelector("div.input.input_full.lk-cv-block__input.input_straight-bottom-right.input_straight-top-right.input_no-border-right.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
        By firstname = By.name("fname");
        By firstnameLatin = By.name("fname_latin");
        By lastname = By.name("lname");
        By lastnameLatin = By.name("lname_latin");
        By blogname = By.name("blog_name");
        By birth = By.name("date_of_birth");
        By country = By.cssSelector("div.select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select");
        By countryValue = By.xpath("//button[contains(text(),'Россия')]");
        By valueOfCountry = By.xpath("//input[@name='country']/following-sibling::div");
        By city = By.xpath("//input[@data-title='Город']/..");
        By cityValue = By.xpath("//button[contains(text(),'Санкт-Петербург')]");
        By valueOfCity = By.xpath("//input[@data-title='Город']/following-sibling::div");
        By languageLevel = By.xpath("//input[@data-title='Уровень знания английского языка']/..");
        By languageLevelValue = By.xpath("//button[contains(text(),'Выше среднего (Upper Intermediate)')]");
        By valueOfLanguageLevel = By.xpath("//input[@data-title='Уровень знания английского языка']/following-sibling::div");


        By vk = By.xpath("//button[contains(text(),'VK')]");
        By facebook = By.xpath("//button[contains(text(),'Facebook')]");
        By contactValueOne = By.id("id_contact-0-value");
        By contactValueTwo = By.id("id_contact-1-value");
        By add = By.xpath("//button[contains(text(),'Добавить')]");
        By save = By.xpath("//button[contains(text(),'Сохранить и продолжить')]");

        logger.info("Открыть website OTUS");

        init();

        driver.get(cfg.urlOTUS());//driver.get("http://otus.ru");

        Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(otusHeader)).isEnabled());

        authFormComponent.auth(driver, wait, logger);
        openLKclass.openLK(wait, logger);

        logger.info("Открываем секцию О СЕБЕ");
        wait.until(ExpectedConditions.elementToBeClickable(aboutMe))
                .click();

        logger.info("Редактируем Персональные данные");

        logger.info("Вводим ИМЯ");
        wait.until(ExpectedConditions.elementToBeClickable(firstname))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(firstname))
                .sendKeys("Оксана");

        logger.info("Вводим ИМЯ (латиницей)");
        wait.until(ExpectedConditions.elementToBeClickable(firstnameLatin))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(firstnameLatin))
                .sendKeys("Oksana");
        logger.info("Вводим ФАМИЛИЮ");
        wait.until(ExpectedConditions.elementToBeClickable(lastname))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(lastname))
                .sendKeys("Курилас");

        logger.info("Вводим ФАМИЛИЮ (латиницей)");
        wait.until(ExpectedConditions.elementToBeClickable(lastnameLatin))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(lastnameLatin))
                .sendKeys("Kurilas");

        logger.info("Вводим Имя (в блоге)");
        wait.until(ExpectedConditions.elementToBeClickable(blogname))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(blogname))
                .sendKeys("Оксана");

        logger.info("Вводим Дату рождения");
        wait.until(ExpectedConditions.elementToBeClickable(birth))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(birth))
                .sendKeys("21.09.1988");

        actions
                .sendKeys(Keys.SPACE)
                .perform();

        logger.info("Редактируем Основную информацию");

        logger.info("Вводим Страну");
        wait.until(ExpectedConditions.presenceOfElementLocated(country))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(countryValue))
                .click();
        logger.info("Вводим Город");
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(city))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(cityValue))
                .click();
        logger.info("Вводим Уровень английского");
        wait.until(ExpectedConditions.presenceOfElementLocated(languageLevel))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(languageLevelValue))
                .click();


        actions
                .sendKeys(Keys.SPACE)
                .perform();


        logger.info("Вводим первый контакт 'ВК'");
        wait.until(ExpectedConditions.presenceOfElementLocated(contactOption))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(vk))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(contactValueOne))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(contactValueOne))
                .sendKeys("VK");

        logger.info("Вводим второй контакт 'ФБ'");
        wait.until(ExpectedConditions.elementToBeClickable(add))
                .click();

        List<WebElement> li = driver.findElements(contactOption);
        li.get(1).click();

        List<WebElement> fb = driver.findElements(facebook);
        fb.get(1).click();

        wait.until(ExpectedConditions.elementToBeClickable(contactValueTwo))
                .clear();
        wait.until(ExpectedConditions.elementToBeClickable(contactValueTwo))
                .sendKeys("FB");

        logger.info("Сохраняем");
        wait.until(ExpectedConditions.elementToBeClickable(save))
                .click();


        driver.close();

        init();


        logger.info("Драйвер поднят");

        driver.get(cfg.urlOTUS());//driver.get("http://otus.ru");


        authFormComponent.auth(driver, wait, logger);
        openLKclass.openLK(wait, logger);

        wait.until(ExpectedConditions.elementToBeClickable(aboutMe))
                .click();

        logger.info("Финальная проверка");

        String valueFirstName = wait.until(ExpectedConditions.presenceOfElementLocated(firstname)).getAttribute("value");
        logger.info("ИМЯ " + valueFirstName);
        Assert.assertTrue(valueFirstName.contains("Оксана"));
        String valueFirstNameLatin = wait.until(ExpectedConditions.presenceOfElementLocated(firstnameLatin)).getAttribute("value");
        logger.info("ИМЯ (латиницей) " + valueFirstNameLatin);
        Assert.assertTrue(valueFirstNameLatin.contains("Oksana"));
        String valueLastName = wait.until(ExpectedConditions.presenceOfElementLocated(lastname)).getAttribute("value");
        logger.info("Фамилия " + valueLastName);
        Assert.assertTrue(valueLastName.contains("Курилас"));
        String valueLastNameLatin = wait.until(ExpectedConditions.presenceOfElementLocated(lastnameLatin)).getAttribute("value");
        logger.info("Фамилия (латиницей) " + valueLastNameLatin);
        Assert.assertTrue(valueLastNameLatin.contains("Kurilas"));
        String valueFistNameInBlog = wait.until(ExpectedConditions.presenceOfElementLocated(blogname)).getAttribute("value");
        logger.info("Имя (в блоге) " + valueFistNameInBlog);
        Assert.assertTrue(valueFistNameInBlog.contains("Оксана"));
        String valueDateOfBirth = wait.until(ExpectedConditions.presenceOfElementLocated(birth)).getAttribute("value");
        logger.info("Дата рождения " + valueDateOfBirth);
        Assert.assertTrue(valueDateOfBirth.contains("21.09.1988"));

        actions
                .sendKeys(Keys.SPACE)
                .perform();

        String CountryCheck = wait.until(ExpectedConditions.presenceOfElementLocated(valueOfCountry)).getText();
        logger.info("Страна " + CountryCheck);
        Assert.assertTrue(CountryCheck.contains("Россия"));
        String CityCheck = wait.until(ExpectedConditions.presenceOfElementLocated(valueOfCity)).getText();
        logger.info("Город " + CityCheck);
        Assert.assertTrue(CityCheck.contains("Санкт-Петербург"));
        String LanguageCheck = wait.until(ExpectedConditions.presenceOfElementLocated(valueOfLanguageLevel)).getText();
        logger.info("Язык " + LanguageCheck);
        Assert.assertTrue(LanguageCheck.contains("Выше среднего (Upper Intermediate)"));

        actions
                .sendKeys(Keys.SPACE)
                .perform();

        String contactOne = wait.until(ExpectedConditions.presenceOfElementLocated(contactOption)).getText();
        logger.info(contactOne);
        Assert.assertTrue(contactOne.contains("Facebook"));

        List<WebElement> contactTwoValue = driver.findElements(contactOption);
        String contactTwo = contactTwoValue.get(1).getText();
        logger.info(contactTwo);
        Assert.assertTrue(contactTwo.contains("VK"));

    }

    private void init(){
        driver = new ChromeDriver();
        logger.info("драйвер поднят");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }


}


