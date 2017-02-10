package oopproberen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alexanderboffin on 11/01/17.
 */
public class Step2SendNewSearch {
    public WebDriver driver;



    public WebDriver typeSearchElement(WebDriver thedriver, String searchelement) {
        //cleaning the previous element and going to
        driver=thedriver;


        System.out.println("got to the function2");

        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("gs_htif0")).sendKeys(searchelement);
        System.out.println("here we are");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("and is it filled in?");

        return driver;
    }
}
