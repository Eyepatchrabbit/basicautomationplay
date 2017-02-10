package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

/**
 * Created by alexanderboffin on 3/01/17.
 */
public class IframesOnPage01 {
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
        //Seeing how manny iframes there are on the pages in question

        System.out.println("Starting the test");

        //iframes on the page
        int framenumbers;

        //"http://rascals.katbox.net","http://www.w3schools.com/html/html_iframe.asp","https://tools.contribute.be", "http://www.guru99.com/handling-iframes-selenium.html","http://www.guru99.com/selenium-tutorial.html"
        String[] thesites={"http://www.guru99.com","http://www.guru99.com/selenium-tutorial.html"};

        for (String site:thesites) {
            driver.get(site);
            framenumbers = driver.findElements(By.tagName("iframe")).size();

            System.out.println("the number of iframes on site "+site+" is: " + framenumbers);
        }


    }
}
