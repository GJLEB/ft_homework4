import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class Test1 extends BaseRunner {

    @Test
    public void test1()  {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        driver.findElement(By.xpath("//*[@name='phone']")).click();
        driver.findElement(By.xpath("//*[@name='city']")).click();
        driver.findElement(By.xpath("//*[@class='SelectItem__contentWrapper_3eEeN']")).click();
        driver.findElement(By.xpath("//*[@class='Header__header_3Teza']")).click();
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//div[@class='Row__row_AjrJL']//div[1]//*[@class='Error__errorMessage_q8BBY']")).getText()); //ФИО
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[2]/div[1]/div[2]")).getText()); //Почта
        assertEquals("Необходимо указать номер телефона",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[2]/div[2]/div[2]")).getText()); //Телефон
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[3]/div/div[2]")).getText()); //Город
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[4]/div/div[2]")).getText()); //Вакансия
    }


    @Test
    public void test2() {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("[name='fio']")).click();
        driver.findElement(By.cssSelector("[name='fio']")).clear();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("Fafdsdfsf");
        driver.findElement(By.cssSelector("[name='email']")).click();
        driver.findElement(By.cssSelector("[name='email']")).clear();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("asdasdas");
        driver.findElement(By.cssSelector("[name='phone']")).click();
        driver.findElement(By.cssSelector("[name='phone']")).clear();
        driver.findElement(By.cssSelector("[name='phone']")).sendKeys("+7 (123) 211-23-12");
        driver.findElement(By.cssSelector("[name='city']")).click();
        driver.findElement(By.cssSelector("[name='city']")).clear();
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("12312asdsd1312sad");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
    }

    @Test
    public void test3(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://www.google.ru/");
        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф");

        //ожидание, игнорирующее StaleElementReferenceException
        wait
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Что-то пошло не так...")
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    //список поисковой выдачи
                    By listItems = By.xpath("//*[contains(@id,'sbse')]/div[2]/b");
                    List<WebElement> elements = driver.findElements(listItems);
                    for (WebElement el : elements) {
                        //из списка вариантов дожиаемся появления нужного, кликаем
                        if (el.getText().equals("тарифы")) {
                            el.click();
                        }
                        break;
                    }
                    //Ожидание появления заголовка
                    return d.getTitle().equals("мобайл тинькофф тарифы - Поиск в Google");
                });

        //Переход на новую вкладку
        wait.until(d ->  d.findElement(By.xpath("//*[@href='https://www.tinkoff.ru/mobile-operator/tariffs/']")));
        driver.findElement(By.xpath("//*[@href='https://www.tinkoff.ru/mobile-operator/tariffs/']")).click();
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
            }
            return check;
        });

        //Закрытие старой вкладки
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        wait.until(d -> {
            d.switchTo().window(tabs.get(0));
            d.close();
            d.switchTo().window(tabs.get(1));
            boolean check;
            check = d.getCurrentUrl().equals("https://www.tinkoff.ru/mobile-operator/tariffs/");
            return check;
        });
    }


    @Test
    public void test4() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("//*[@id='x50d87']/div/div/div/div/div/div[3]/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]")).click();

        chekelement("//*[@id='x50d87']/div/div/div/div/div/div[2]","Москва и Московская область");
        driver.navigate().refresh();

        chekelement("//*[@id='x50d87']/div/div/div/div/div/div[2]","Москва и Московская область");
        String s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        int defMsk=Integer.parseInt(s.replaceAll("\\D+",""));

        Select select = new Select();
        select.set("Интернет","Безлимитный интернет",driver);
        select.set("Звонки","Безлимитные минуты",driver);
        CheckBox checkBox = new CheckBox();
        checkBox.click("Режим модема",driver);
        checkBox.click("Безлимитные SMS",driver);
        s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        int maxMsk=Integer.parseInt(s.replaceAll("\\D+",""));

        driver.findElement(By.xpath("//*[text()='Москва и Московская область']")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div[1]")).click();

        s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        int defKrsn=Integer.parseInt(s.replaceAll("\\D+",""));

        select.set("Интернет","Безлимитный интернет",driver);
        select.set("Звонки","Безлимитные минуты",driver);
        checkBox.click("Режим модема",driver);
        checkBox.click("Безлимитные SMS",driver);
        s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        int maxKrsn=Integer.parseInt(s.replaceAll("\\D+",""));

        if(defKrsn==defMsk) {
            throw new InvalidArgumentException("Дефолтные цены в Краснодаре и Москве одинаковые");
        }
        if(maxKrsn!=maxMsk) throw new InvalidArgumentException("Максимальные цены в Краснодаре и Москве разные");


    }

    @Test
    public void test5(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("//*[@id='x50d87']/div/div/div/div/div/div[3]/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]")).click();
        Select select = new Select();
        CheckBox checkBox = new CheckBox();
        select.set("Интернет","0 ГБ",driver);
        select.set("Звонки","0 минут",driver);
        checkBox.click("Мессенджеры",driver);
        checkBox.click("Социальные сети",driver);
        String s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        int price=Integer.parseInt(s.replaceAll("\\D+",""));
        if (price!=0) throw new InvalidArgumentException("Цена не равна нулю");
        if(driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[4]/div/div[2]/div/div/div/div/button")).isEnabled()) throw new IllegalArgumentException("Кнопка активна при, отсутствии выбранных опций.");
    }

    public void chekelement(String xpath, String res){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> {
            boolean check;
            check = d.findElement(By.xpath(xpath)).getText().equals(res);
            return check;
        });
    }

}

