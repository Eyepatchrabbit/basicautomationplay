package tryfurtherselenium;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * Created by alexanderboffin on 5/01/17.
 */
public class tryingAlerthandeling {
    RemoteWebDriver driver;


    @BeforeTest
    public  void setDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();

    }


    @AfterTest
    public void cleanup(){
        System.out.println("cleaning actions");
        driver.quit();
    }

    @Test
    public  void testingAllert() {
        // Alert Message handling
        driver.get("http://demo.guru99.com/V4/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait= new WebDriverWait(driver,10);

        driver.findElement(By.name("uid")).sendKeys("mngr30127");
        driver.findElement(By.name("password")).sendKeys("EzAtAqy");
        driver.findElement(By.name("btnLogin")).submit();

        /*see if everything is alright
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage=driver.switchTo().alert().getText();
        System.out.println(alertMessage);

        //accept alert
        driver.switchTo().alert().accept();


    }


}

