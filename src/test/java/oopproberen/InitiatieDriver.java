package oopproberen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by alexanderboffin on 11/01/17.
 */
public class InitiatieDriver {
    public static WebDriver driver;

    @BeforeTest
    public void open_browser()
    {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        System.out.println("have started initiation");

    }

    @AfterTest
    public void close_browser()
    {

        driver.quit();
    }
}
