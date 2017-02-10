package onthegrid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Christel Van Aerschot on 19/12/16.
 */
public class chromeDriver {
    private RemoteWebDriver driver;

    @Before
    public  void launchDriver() throws MalformedURLException {
            System.out.println(" Executing on Chrome");
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            System.out.println("Chrome driver set");

            //setting an implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void searchGoogle() {
        //just lancing a site to see if this works
        driver.get("http://calmware.be/contact-2");
        driver.findElement(By.id("us_form_1_name")).sendKeys("name");
    }

    @After
    public void cleanUp() {
        driver.quit();

        System.out.println("We did it");
    }

}
