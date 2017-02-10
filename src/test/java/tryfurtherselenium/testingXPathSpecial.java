package tryfurtherselenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class testingXPathSpecial {


    @Test
    public void GoogleSearch() {
        /* Probeer dit later als functie te schrijven=>?makkelijker overpakken, herbruiken?
        * OF probeer het in begin te zetten (direct na class)
        * */
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        //Doing the tests
        String thesite="http://demo.guru99.com/v4/";
        String tosearch="here";

        //execution
        driver.get(thesite);


        System.out.println("Now on webpage "+thesite+"\n\t looking for element:"+tosearch);
        //int inornot=driver.findElements(By.xpath("//*[contains(text()=\"here\")]")).size();

        //int inornot=driver.findElements(By.xpath("//li[1]/a[contains(text(),\"here\")]")).size();
        int inornot=driver.findElements(By.xpath("//li[1]/a[contains(text(),\""+tosearch+"\")]")).size();

        System.out.println("The result "+inornot);





        //cleanup
        //close the window
        System.out.println("cleaning actions start");
        driver.quit();
        System.out.println("cleaning actions finished");

    }
}
