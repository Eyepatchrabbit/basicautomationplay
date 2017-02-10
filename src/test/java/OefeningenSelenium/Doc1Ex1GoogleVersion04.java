package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc1Ex1GoogleVersion04 {


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



        //Doing the tests
        //System.out.println("eerste dee: GUI");
        String[] wikizoek={"GUI Tests","Load Tests"};
        driver.get("http://www.google.be");
        for (String zoeker:wikizoek) {

            driver.findElement(By.id("sb_ifc0")).sendKeys(zoeker);
            driver.findElement(By.name("btnG")).click();
            //Nu kijken naar text
            WebDriverWait wait= new WebDriverWait(driver,2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.st")));
            String textwiki = driver.findElement(By.cssSelector("span.st")).getText();
            System.out.println(textwiki);
            String InWiki = textwiki.split("\\.\\.\\.")[0];
            System.out.println(InWiki);

            //click door naar wikipage  to look als het dezelfde text is
            driver.findElement(By.partialLinkText("Wikipedia")).click();
            String TextInWiki = driver.findElement(By.xpath("//body/div[3]/div[3]/div[4]/p[1]")).getText();
            System.out.println(TextInWiki);
            Assert.assertTrue(TextInWiki.contains(InWiki));
            System.out.println("assert succesful");
            driver.navigate().back();
            driver.findElement(By.id("lst-ib")).clear();
        }


        //cleanup
        //close the window
        System.out.println("cleaning actions");
        driver.quit();

    }
}
