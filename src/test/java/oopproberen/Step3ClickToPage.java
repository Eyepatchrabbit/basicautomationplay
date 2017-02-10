package oopproberen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class Step3ClickToPage {


    public static void ClickOnButton(WebDriver driver) {
        driver.findElement(By.name("btnG")).click();

        System.out.println("we pressed on");
    }


}
