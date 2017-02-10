package Try;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by alexanderboffin on 28/11/16.
 */
public class Oefenen {
    @Test
     public void Alex01() {

        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.calmware.be");


        //close the window
        driver.quit();

    }
}