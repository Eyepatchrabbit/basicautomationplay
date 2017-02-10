package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 3/01/17.
 */
public class IFramesSelection01 {
    WebDriver driver;

    @BeforeMethod
    public void settingDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void theTestLand() {


        System.out.println("Starting the test");

        driver.get("http://www.guru99.com/selenium-tutorial.html");

        //Are trying to go to the DataCamp website trough the iframe!
        driver.switchTo().frame("aswift_0");

        System.out.println("switched1");

        driver.switchTo().frame("google_ads_frame1");

        System.out.println("switched2");

        String thesrc= driver.findElement(By.cssSelector("img.img_ad")).getAttribute("src");


        System.out.println("The srs of the first advertisement is "+thesrc);

    }


}


