package Try;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 15/12/16.
 */
public class tryDriverSelenium01 {
    RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public  void launchDriver(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println(" Executing on FireFox");


            DesiredCapabilities capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");

            //setting the driver
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            System.out.println("Firefox driver set");

            //setting an implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        }
        else{
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }


    }

    @org.testng.annotations.Test
    public void testDriverSelen() {
        //just lancing a site to see if this works
        driver.get("http://rascals.katbox.net/");

        //click on something=>going to an other site
        WebDriverWait wait= new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt=\"Twokinds\"]")));
        driver.findElement(By.cssSelector("img[alt=\"Twokinds\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        //see if we got to here
        System.out.println(driver.findElement(By.cssSelector("h1")).getText());

    }

    @AfterTest
    public void cleanUp() {
        driver.quit();

        System.out.println("We did it");
    }

}
