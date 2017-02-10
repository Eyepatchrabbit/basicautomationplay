package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 3/01/17.
 */
public class IFramesIndexingDisection01 {
    WebDriver driver;

    @BeforeMethod
    public void settingDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.guru99.com/selenium-tutorial.html");

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void theTestLand() {


        System.out.println("Starting the test");



        //a program to test the indexnumbering

        int numberofiframes=driver.findElements(By.tagName("iframe")).size();
        System.out.println("Total counted numbers of frames"+numberofiframes);




        for (int framenumber=0;framenumber<numberofiframes;framenumber++){


            System.out.print("frame: "+framenumber);
            driver.switchTo().frame(framenumber);
            int nownumberofframes=driver.findElements(By.tagName("iframe")).size();
            System.out.println(" Number of frames here : "+nownumberofframes);
            driver.switchTo().parentFrame();
        }
        System.out.println("finished loop");

    }



}




