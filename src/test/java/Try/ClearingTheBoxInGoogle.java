package Try;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ClearingTheBoxInGoogle {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.be");
        driver.findElement(By.id("gs_htif0")).sendKeys("Contribute");
        driver.findElement(By.name("btnG")).click();
        //mogelijkheid 1 voor te verwijderen=>methode werkt!
        //driver.findElement(By.id("lst-ib")).sendKeys(Keys.chord(Keys.COMMAND,"a"));
        //driver.findElement(By.id("lst-ib")).sendKeys(Keys.BACK_SPACE);


        //2e mogelijkheid=>proberen  in 1 lijn te krijgen
        //driver.findElement(By.id("lst-ib")).sendKeys(Keys.chord(Keys.COMMAND,"a")+ Keys.BACK_SPACE);//werkt niet om een of andere reden
        driver.findElement(By.id("lst-ib")).clear();//MAAR deze werkt wel!!!


        //close the window
        driver.quit();
    }
}
