
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GoogleMain;
import pages.GoogleResult;
import pages.TinkoffJob;
import pages.TinkoffMobile;


import java.util.concurrent.TimeUnit;



public class Application {
    Logger logger = LoggerFactory.getLogger(Application.class);
    private WebDriverWait wait;
    private WebDriver driver;
    public GoogleMain google;
    public GoogleResult googleResults;
    public TinkoffJob tinkoffJob;
    public TinkoffMobile tinkoffMobile;


    public Application() {
        driver = new EventFiringWebDriver(getDriver());
        ((EventFiringWebDriver) driver).register(new BrowsersFactory.MyListener());
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        google = new GoogleMain(driver);
        googleResults = new GoogleResult(driver);
        tinkoffJob = new TinkoffJob(driver);
        tinkoffMobile = new TinkoffMobile(driver);
    }
    public void quit() {
        driver.quit();
        driver = null;
    }
    private WebDriver getDriver() {
        return BrowsersFactory.buildDriver();
    }
}