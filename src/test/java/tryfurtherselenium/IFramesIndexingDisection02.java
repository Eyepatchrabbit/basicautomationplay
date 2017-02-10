package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 3/01/17.
 */
public class IFramesIndexingDisection02 {
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

        /*Found string on http://stackoverflow.com/questions/14812087/how-to-get-frame-names-in-a-page
        =>modified it to take a look at the elements in the webpage and get an idea of the indexing vs visual presentation
        */

        List<WebElement> ele = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of frames in a page :" + ele.size());
        int indexofframe=1;

        for(WebElement el : ele){
            //Returns the Id of a frame.
            System.out.println("IFrame at place "+indexofframe+" of the page has Id:" + el.getAttribute("id"));
            //Returns the Name of a frame.
            //System.out.println("Frame name :" + el.getAttribute("name"));

            indexofframe=indexofframe+1;

        }

    }



}




