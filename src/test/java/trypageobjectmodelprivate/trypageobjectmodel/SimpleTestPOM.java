package trypageobjectmodelprivate.trypageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class SimpleTestPOM {
    WebDriver driver;
    searchandfind searchObject;
    CompareTextGoogleWiki comparetexts;


    @BeforeTest
    public void open_browser()
    {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        System.out.println("have started initiation");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterTest
    public void close_browser()
    {
        driver.quit();
    }


    @Test
    public  void testGoogleExercise() {
        //Same as trypageobjectmodel. Only the elements in the not-called classes are made private
        //=>this comes in handy=> can then only select the "masterelements" that we want to use

        driver.get("https://www.google.be/");

        //create search on google site =>here place driver we are working on!
        searchObject=new searchandfind(driver);
        //can now do search
        searchObject.placeSearchOnGoogle("GUI Tests");

        /*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        comparetexts=new CompareTextGoogleWiki(driver);
        comparetexts.compateGoogleAndWikiTexts();

    }

}
