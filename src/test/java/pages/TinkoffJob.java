package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;


public class TinkoffJob extends Page {
    public TinkoffJob(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void typeForm( String fio, String email,String phone,String city){
        //Заполняем форму максиально быстро, пытаясь игнорировать анимацию страницы
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.xpath("//*[@name='fio']")).sendKeys(fio);
                    driver.findElement(By.xpath("//*[@name='email']")).sendKeys(email);
                    driver.findElement(By.xpath("//*[@name='phone']")).sendKeys(phone);
                    driver.findElement(By.xpath("//*[@name='city']")).sendKeys(city);
                    driver.findElement(By.xpath("//*[@class='SelectItem__contentWrapper_3eEeN']")).click();
                    driver.findElement(By.xpath("//*[@class='Header__header_3Teza']")).click();
                    return true;
                });
    }

    public  void errorsCheck(){
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//div[@class='Row__row_AjrJL']//div[1]//*[@class='Error__errorMessage_q8BBY']")).getText()); //ФИО
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[2]/div[1]/div[2]")).getText()); //Почта
        assertEquals("Необходимо указать номер телефона",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[2]/div[2]/div[2]")).getText()); //Телефон
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[3]/div/div[2]")).getText()); //Город
        assertEquals("Поле обязательное",driver.findElement(By.xpath("//*[@id='form']/div/article/div[2]/div/form/div[4]/div/div[2]")).getText()); //Вакансия
    }

}