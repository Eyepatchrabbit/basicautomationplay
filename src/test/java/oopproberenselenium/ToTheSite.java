package oopproberenselenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 11/01/17.
 */
public class ToTheSite extends ToTheSiteInitiatieDriver{

    @Test
    public static void typeSearchElement() {
        //oefening op inheritence met selenium driver (kijken op mogelijkheid oevrerving)

        driver.findElement(By.id("gs_htif0")).sendKeys("Contribute");
        System.out.println("here we are");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("and is it filled in?");
    }
}
