import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

 class CheckBox extends BaseRunner {
    void click(String name, WebDriver driver){
        driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", name))).click();


    }


}
