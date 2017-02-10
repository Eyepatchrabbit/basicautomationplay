package Try;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Created by alexanderboffin on 28/11/16.
 */
public class Laathetwerken {

    @Test
    public void Alex01(){


        WebDriver driver = new SafariDriver();
        driver.get("http://www.calmware.be");

        driver.quit();
    }
}
