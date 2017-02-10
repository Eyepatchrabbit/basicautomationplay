package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc5Ex2WhileDogVersion03 {


    @Test
    public void main() {//Only now saw this=>rest kept the original from google
        /* ---------------------------------------------------------------------------------
        the starting elements for running the test
        *-----------------------------------------------------------------------------------*/
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //setting start of explicit wait=>reusable over the entire test
        WebDriverWait wait= new WebDriverWait(driver,2);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop/Category.aspx?categoryId=DOGS");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        //List all the values that you can find for dogs with a “while” loop

        //beginVariabelen=> om te veranderen
        String[]PuppiePound={"Bulldog","Chihuahua","Dalmation","Poodle"};
        Integer numie=1;


        //De test zelf
        String ScrappieDoo=null;
        int length =PuppiePound.length-1;
        while (!PuppiePound[numie-1].equals(PuppiePound[length])) {
            numie++;
            ScrappieDoo = driver.findElement(By.xpath("//tr["+numie+"]/td[2]/a")).getText();
            System.out.println(ScrappieDoo);
            Assert.assertEquals(PuppiePound[numie-2],ScrappieDoo);

        }
        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();

    }
}
