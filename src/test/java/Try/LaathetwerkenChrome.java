package Try;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 28/11/16.
 */
public class LaathetwerkenChrome {

    @Test
    public void Alex01(){

        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,20);

        driver.get("http://www.sdamned.com/");



        /* Trying to get elements with chromedriver*/

        String thetitle=driver.getTitle();

        //driver.findElement(By.id("extras")).click();

        WebElement clickelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("extras")));
        clickelement.click();


        driver.quit();
    }
}
