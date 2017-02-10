package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc1Ex1Google {


    @Test
    public void GoogleSearch() {
        /* Probeer dit later als functie te schrijven=>?makkelijker overpakken, herbruiken?
        * OF probeer het in begin te zetten (direct na class)
        * */
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Starting page
        driver.get("http://www.google.be");


        //Doing the tests
        //System.out.println("eerste dee: GUI");
        driver.findElement(By.id("sb_ifc0")).sendKeys("GUI Tests");
        driver.findElement(By.name("btnG")).click();
        //Nu kijken naar text
        String textwiki =driver.findElement(By.cssSelector("span.st")).getText();
        System.out.println(textwiki);
        String InWiki = textwiki.split("\\.\\.\\.")[0];
        System.out.println(InWiki);

        //click door naar wikipage  to look als het dezelfde text is
        driver.findElement(By.partialLinkText("Wikipedia")).click();
        String TextInWiki =driver.findElement(By.xpath("//body/div[3]/div[3]/div[4]/p[1]")).getText();
        System.out.println(TextInWiki);
        Assert.assertTrue(TextInWiki.contains(InWiki));
        System.out.println("hello");
        //cleanup
        //close the window
        System.out.println("cleaning actions");
        driver.quit();

    }
}