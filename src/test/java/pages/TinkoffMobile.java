package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;

public class TinkoffMobile extends Page {
    public TinkoffMobile(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkRegion(String region){
        wait.until(d -> d.findElement(By.xpath("//*[@class='MvnoRegionConfirmation__title_3WFCP']")).getText().contains(region));
        return true;

    }

    public int getPrice(){
        String s=driver.findElement(By.xpath("//*[@id='form-application']/div[2]/div/form/div[1]/div[1]/div/h3")).getText();
        return (Integer.parseInt(s.replaceAll("\\D+","")));
    }

}
