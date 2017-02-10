package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc5Ex4FindTheCatv01 {


    @Test
    public void main() {//Only now saw this=>rest kept the original from google
        /* ---------------------------------------------------------------------------------
        the starting elements for running the test=>reusable over tests
        *-----------------------------------------------------------------------------------*/
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //setting start of explicit wait=>reusable over the entire test
        WebDriverWait wait= new WebDriverWait(driver,2);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop/SignIn.aspx");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        //Loop over the values in the navigation bar, if the value is “Cats”, click on the link and verify if you have navigated to the correct page

        //beginVariabelen=> om te veranderen
        String[] ToCheck={"Fish","Dogs","Reptiles","Cats","Birds"};
        String ToFind= "Cats";
        int Numie=1;
        //Deze element gewoon erin gezet voor de lol
        String BreakOn="No";//Als je de lijst ToCheck wilt afwerken na vinden element =>set "No". Otherwise "Yes"

        //The actual tests
        for (String CATegorie:ToCheck){
            String Cathy=driver.findElement(By.xpath("//table[3]/tbody/tr/td[2]/font/a["+Numie+"]")).getText();
            System.out.println("We got:"+Cathy+" -- We wanted:"+CATegorie);
            Assert.assertTrue(Cathy.equals(CATegorie));
            if (CATegorie.equals(ToFind)){
                //Go to page & Test if on THE RIGHT page
                driver.findElement(By.xpath("//table[3]/tbody/tr/td[2]/font/a["+Numie+"]")).click();
                System.out.println("We have found "+ToFind);
                String ThePage=driver.findElement(By.xpath("//*[@id=\"lblPage\"]")).getText();
                Assert.assertTrue(ThePage.equals(ToFind.toUpperCase()));
                System.out.println("We Are on the right page ");
                //If we want to stop looking at the oter elements in the loop=>BreakOn=="Yes"
                if (BreakOn.equals("Yes")){
                    break;
                }
                //back to previos to complete loop=>If BreakOn=="No"->looks at rest list
                driver.navigate().back();
            }
            Numie++;
        }


        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();

    }
}
