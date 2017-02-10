package oopproberen;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import oopproberen.Step1SendSearchRequest;

/**
 * Created by alexanderboffin on 11/01/17.
 */
public class TestingItAll extends oopproberenselenium.ToTheSiteInitiatieDriver{
    //Proberen splitsen  stappen over verschillende classes

    @Test
    public void typeSearchElement() {

        driver.get("https://www.google.be/");

        WebDriverWait wait=new WebDriverWait(driver,10);

        //actions of the elements in a different class
        new Step1SendSearchRequest().typeSearchElement(driver,"Contribute");

        //driver.findElement(By.name("btnG")).click();
        new Step3ClickToPage().ClickOnButton(driver);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));

        new Step2SendNewSearch().typeSearchElement(driver,"new element");

        System.out.println("Do or do not.");
    }
}



