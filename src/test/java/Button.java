import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


class Button extends BaseRunner {
    boolean isAvalibale(WebDriver driver){
        return driver.findElement(By.xpath("//*[@class='LoaderRound__container_no-background_GvpfD LoaderRound__container_coverParent_2-_fi']")).isEnabled();
    }

}