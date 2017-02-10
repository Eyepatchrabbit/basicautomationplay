package Try;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 28/11/16.
 */
public class LaathetwerkenvOverFunctiesB {
    /*The basics*/
    WebDriver driver;

    /*The test itself*/
    @Test
    public  void tryingItThisWay() {
        theTest();
    }

    public  void setDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();

    }



    public void cleanup(){
        driver.quit();
    }


    /*Functions for using in the test*/
    public String getTitle() {
        String thetitle=driver.getTitle();
        return thetitle;
    }




    public void theTest(){
        setDriver();
        /*needed for the test*/
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //setting start of explicit wait=>reusable over the entire test
        WebDriverWait wait= new WebDriverWait(driver,5);

        driver.get("http://drawabox.com");
        System.out.println("Hello world");


        //Wait for loading page before next step:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.hidden-xs")));


        /*proberen van een paar functies*/
        String thetitle=getTitle();
        System.out.println(thetitle);

        cleanup();

    }



}
