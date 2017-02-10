package Try;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by alexanderboffin on 28/11/16.
 */
public class Laathetwerkenv3 {
    WebDriver driver;
    @Test
    public void Alex01(){
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://www.calmware.be");
        System.out.println("Hello world");
        driver.quit();
    }
}
