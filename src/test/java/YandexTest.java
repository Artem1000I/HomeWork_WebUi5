import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YandexTest extends AbstractTest {
    @Order(1)
    @Test
    void myActiontest() throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://yandex.ru/pogoda/?lat=43.785049&lon=131.971069&win=564"));
        String URL = getDriver().getCurrentUrl(); //Проверяем урл главной страницы
        Assertions.assertEquals(URL, "https://yandex.ru/pogoda/?lat=43.785049&lon=131.971069&win=564" );
    }
    //Регистрация
    @Order(2)
    @Test
    void inputTest() {

        WebElement loginButton = getDriver().findElement(By.xpath("//span[text()='Войти']"));
        loginButton.click();

        WebElement inputEmail = getDriver().findElement(By.id("passp-field-login"));
        inputEmail.click();
        inputEmail.sendKeys("AnTONTON111");
        WebElement signIn = getDriver().findElement(By.id("passp:sign-in"));
        signIn.click();

        WebElement passowrd = getDriver().findElement(By.id("passp-field-passwd"));
        passowrd.sendKeys("Anton111anto");
        WebElement signIn2 = getDriver().findElement(By.id("passp:sign-in"));
        signIn2.click();
        try {
            Thread.sleep(3000); //заснуть на 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Assert при не верном вводе пароля
        WebElement errorMessage = getDriver().findElement(By.id("field:input-passwd:hint"));
        Assertions.assertEquals(errorMessage.getText(),"Неверный пароль");

        WebElement passowrd1 = getDriver().findElement(By.id("passp-field-passwd"));
        passowrd1.sendKeys("Anton111anton");
        WebElement signIn3 = getDriver().findElement(By.id("passp:sign-in"));
        signIn3.click();
        //Assert вводимые данные
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Проверка авторизации
    /*@Order(3)
    @Test
    void authorizationTest(){
        WebElement userPhoto = getDriver().findElement(By.cssSelector(".user-pic__image"));
        userPhoto.click();
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//span[text()='Выйти']")).isDisplayed());//Смотрим что присутсвует элемент выйти на вкладке профиля
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    //Работа с картой
    @Order(3)
    @Test
    void mapTest() {
        //Работа с картой,
        //Открыли карту
        WebElement maps =getDriver().findElement(By.xpath(".//div[text()='Показать на карте']"));
        maps.click();
        //Assert появление кнопки геолокации
        Assertions.assertNotNull(getDriver().findElement(By.cssSelector(".b-page button")).isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Order(4)
    @Test
    void
    sectionsMapTest(){
        WebElement maps1 =getDriver().findElement(By.xpath(".//div[text()='Показать на карте']"));
        maps1.click();
        //Смотрим осадки
        WebElement rainfall = getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/span/label[1]"));
        rainfall.click();
        //Assert проверяем что открыта карта осадков.На карте есть чекбокс Молнии .//span[text()='Молнии']
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//span[text()='Молнии']")).isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Смотрим глубину снега
        WebElement nowcast = getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/span/label[2]"));
        nowcast.click();
        //Assert роверяем что появилась плашка с глубиной снег значение в "cм"
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//div[text()='см']")).isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Смотрим температуру
        WebElement temperature = getDriver().findElement(By.xpath(".//input[@value='temperature']")); //.//label/span/a/span/span
        temperature.click();
        // Assert Проверяем что есть на карте появилась шкала температуры значения "°C"
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//div[text()='°C']")).isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Смотрим ветер
        WebElement wind = getDriver().findElement(By.xpath(".//input[@value='wind']"));
        wind.click();
        //Assert Проверяем что есть на карте появилась шкала ветра значения "м/с"
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//div[text()='м/с']")).isDisplayed());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Смотрим давление
        WebElement pressure = getDriver().findElement(By.xpath(".//input[@value='pressure']"));
        pressure.click();
        //Assert Проверяем что есть на карте появилась шкала давления с значением "мм рт. ст"
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//div[text()='мм рт. ст.']")).isDisplayed());
    }
    @Order(5)
    @Test
        void forecastTenDayTest() {
        //Возвращаемся на главную
        WebElement yandex = getDriver().findElement(By.xpath(".//span[text()='Прогноз на 10 дней']"));
        yandex.click();
        //Assert проверяем что находимся на главной
        String URL = getDriver().getCurrentUrl(); //Проверяем урл главной страницы //для данного пользователя
        Assertions.assertEquals(URL, "https://yandex.ru/pogoda/?lat=43.785049&lon=131.971069&via=hnav" );

        //Подробный прогноз погоды на 10 дней
        WebElement tenDay = getDriver().findElement(By.xpath(".//a[text()='Подробный прогноз на 10 дней']"));
        tenDay.click(); // Смотрим по элементу на страницы что перешли на подробный прогноз погоды
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//p[text()='Прогноз погоды в Уссурийске на сегодня, завтра и на ближайшую неделю с точностью до района — рассчитан с помощью ']")).isDisplayed());
        //Скролим до 10го дня
        WebElement iframe = getDriver().findElement(By.xpath(".//h3[text()='Партнёрам']"));
        getDriver().navigate().back();
    }

    @Order(6)
    @Test void searchCityTest(){
        //Поиск города
        WebElement search = getDriver().findElement(By.cssSelector(".mini-suggest__input"));
        search.click();
        search.sendKeys("Мурманск");
        search.sendKeys(Keys.ENTER);
       //Assert Проверяем что есть заголовок "Вы искали: Мурманск"
       Assertions.assertNotNull(getDriver().findElement(By.xpath(".//h1[text()='Вы искали: Мурманск']")).isDisplayed());
        try {
            Thread.sleep(3000); //заснуть на 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Order(7)
    @Test
    void weatherMonthTest() {
        WebElement month = getDriver().findElement(By.xpath(".//a[text()='На месяц']"));
        month.click();
        //Assert смотрим что появилась кнопка Сравнить
        Assertions.assertNotNull(getDriver().findElement(By.xpath(".//span[text()='Сравнить']")).isDisplayed());
    }


}