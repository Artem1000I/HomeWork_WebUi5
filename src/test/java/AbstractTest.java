import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class AbstractTest {
    private static WebDriver driver;

    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incignito");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);//Запускаем хром
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);//не явное ожидание
    }
    @BeforeEach
    void goTo(){
        Assertions.assertDoesNotThrow(()->driver.navigate().to("https://yandex.ru/pogoda/?lat=43.785049&lon=131.971069&win=564"),"Страница не доступна");
    }
    @AfterAll
    static void close(){
    }
    public static WebDriver getDriver(){return driver;}
}
