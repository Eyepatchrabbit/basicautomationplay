package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc5Ex2WhileDogVersion01 {


    @Test
    public void GoogleSearch() {
        /* ---------------------------------------------------------------------------------
        the starting elements
        *-----------------------------------------------------------------------------------*/
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //setting start of explicit wait
        WebDriverWait wait= new WebDriverWait(driver,2);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop/Category.aspx?categoryId=DOGS");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        //List all the values that you can find for dogs with a “while” loop

        //beginelementen
        String ScrappieDoo=null;
        String TheDogsAre="In";
        Integer numie=2;
        while (TheDogsAre=="In") {
            ScrappieDoo = driver.findElement(By.xpath("//tr["+numie+"]/td[2]/a")).getText();
            System.out.println(ScrappieDoo);
            if (ScrappieDoo.equals("Poodle")){
                TheDogsAre="Broken Out";
            }
            numie++;
        }
        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();

    }
}
