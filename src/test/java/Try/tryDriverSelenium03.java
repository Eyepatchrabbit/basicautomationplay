package Try;

import org.junit.Assert;
import org.openqa.selenium.By;
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
public class tryDriverSelenium03 {
    RemoteWebDriver driver;
    String thedriverused;

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

            //the driver used
            thedriverused="Firefox";


        }else if(browser.equalsIgnoreCase("chrome")){
            System.out.println(" Executing on Chrome");

            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            System.out.println("Chrome driver set");

            //setting an implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            thedriverused="Chrome";

        }
        else{
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }


    }

    @org.testng.annotations.Test
    public void testDriverSelen() {
        //just lancing a site to see if this works
        driver.get("http://www.google.be");

        //click on something=>going to an other site
        WebDriverWait wait= new WebDriverWait(driver,10);


        //driver.findElement(By.id("lst-ib")).sendKeys("helloworld");


        String[] wikizoek={"GUI Tests","Load Tests"};   //waarnaar zoeken

        for (String zoeker:wikizoek) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
            driver.findElement(By.id("lst-ib")).sendKeys(zoeker);
            driver.findElement(By.name("btnG")).click();

            //Nu kijken naar text
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.st")));
            String textwiki = driver.findElement(By.cssSelector("span.st")).getText();
            System.out.println(textwiki);
            String inwiki = textwiki.split("\\.\\.\\.")[0];
            System.out.println(inwiki);

            //click door naar wikipage  to look als het dezelfde text is
            driver.findElement(By.partialLinkText("Wikipedia")).click();
            String textinwiki = driver.findElement(By.xpath("//body/div[3]/div[3]/div[4]/p[1]")).getText();
            System.out.println(textinwiki);
            Assert.assertTrue(textinwiki.contains(inwiki));
            System.out.println("assert succesful");
            driver.navigate().back();
            driver.findElement(By.id("lst-ib")).clear();
        }


    }

    @AfterTest
    public void cleanUp() {
        driver.quit();

        System.out.println("We did it"+thedriverused);
    }

}
