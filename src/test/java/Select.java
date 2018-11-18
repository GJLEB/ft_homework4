
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


 class Select extends BaseRunner{


     void set(String type, String name,WebDriver driver) {

        if (type.equals("Интернет")) {
            driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[3]/div/div[1]/div/div/div/div[1]/div/div")).click();
            driver.findElement(By.xpath(String.format("//*[@class='ui-dropdown-field-list__item-text'][text()='%s']", name))).click();
        }
        else {
            driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/div[1]/div/div")).click();
            driver.findElement(By.xpath(String.format("//*[@class='ui-dropdown-field-list__item-text'][text()='%s']", name))).click();

        }
    }
}