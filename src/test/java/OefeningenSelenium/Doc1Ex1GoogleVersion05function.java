package OefeningenSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
public class Doc1Ex1GoogleVersion05function {
    /*---------The basics-----------------------------------------------------------------*/
    WebDriver driver;


    @Before
    public  void setDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();

    }


    @After
    public void cleanup(){
        System.out.println("cleaning actions");
        driver.quit();
    }


    /*----------Functions for using in the test-------------------------------------------*/
    public void googleSearch(String zoeker) {
        /*The point of this function is to recieve a search term (zoeker).
        The function puts the search term in the searchbox.
        It then sees if the wikitext on the ggogle page matches the one in the Wiki
        * */

        //zoekactie uitvoeren op google
        driver.findElement(By.id("sb_ifc0")).sendKeys(zoeker);
        driver.findElement(By.name("btnG")).click();
        //wait for propper loading
        WebDriverWait wait= new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.st")));

        //Looking at the text
        String textwiki = driver.findElement(By.cssSelector("span.st")).getText();
        System.out.println(textwiki);
        String InWiki = textwiki.split("\\.\\.\\.")[0];
        System.out.println(InWiki);

        //Move to the wikipage
        driver.findElement(By.partialLinkText("Wikipedia")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div[3]/div[4]/p[1]")));       //wait for loading

        //See if we find the same text
        String TextInWiki = driver.findElement(By.xpath("//body/div[3]/div[3]/div[4]/p[1]")).getText();
        System.out.println(TextInWiki);
        Assert.assertTrue(TextInWiki.contains(InWiki));
        System.out.println("assert succesful");
        //naar vorige pagina gaan en dan
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
        driver.findElement(By.id("lst-ib")).clear();
    }


    @Test
    public void Thetest() {
        /*setting starting elements*/
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        //Doing the tests
        String[] wikizoek={"GUI Tests","Load Tests"};

        //Starting page
        driver.get("http://www.google.be");
        for (String zoeker:wikizoek) {
            //call the function (see previous) and let it do its thing
            googleSearch(zoeker);
        }


        //did it all work
        System.out.println("Got to the end");

    }
}
