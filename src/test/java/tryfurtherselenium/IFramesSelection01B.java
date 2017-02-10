package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.window;

/**
 * Created by alexanderboffin on 3/01/17.
 */
public class IFramesSelection01B {
    WebDriver driver;

    @BeforeMethod
    public void settingDriver() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.guru99.com/selenium-tutorial.html");

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void theTestLand() {


        System.out.println("Starting the test");



        //a program to test the indexnumbering

        int numberofiframes=driver.findElements(By.tagName("iframe")).size();
        System.out.println("Total counted numbers of frames"+numberofiframes);

        int inornotimage=0;



        for (int framenumber=0;framenumber<numberofiframes;framenumber++){
            driver.switchTo().frame(framenumber);
            //inornot=driver.findElements(By.cssSelector("img.img_ad")).size();
            inornotimage=driver.findElements(By.xpath("html/body/div/a/img[@width=\"336\"]")).size();
            System.out.print(inornotimage);         //see if it is there or not
            int numberofframesnow=driver.findElements(By.tagName("iframe")).size();
            System.out.println(" now the count is: "+numberofframesnow);


            if (inornotimage==1){
                System.out.println("\nFound the element at "+framenumber);
                break;
            }


        }
        if(inornotimage==0) {
            System.out.print("something is amis");
        }
    }


}


