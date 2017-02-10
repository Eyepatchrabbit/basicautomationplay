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
public class Doc5Ex5FAdvancedv01 {


    @Test
    public void main() {//Only now saw this=>rest kept the original from google
        /* ---------------------------------------------------------------------------------
        the starting elements for running the test=>reusable over tests
        *-----------------------------------------------------------------------------------*/
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //setting start of explicit wait=>reusable over the entire test
        WebDriverWait wait= new WebDriverWait(driver,5);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop/SignIn.aspx");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        /*	ADVANCED EXERCISE: Add some animals to your Shopping Cart. Try with use of loops and conditional statements to remove and add the first animal to your shopping cart. You need to break the loop when you have found the animal and use XPath to identify the elements in this exerces:
        Cats > Persian > Adult Female
        Dogs > Poodle > Male Puppy
        =>ik ga proberen de vorige code te hergebruiken
         */

        //beginVariabelen=> om te veranderen=>past test aan
        String[] ToCheck={"Fish","Dogs","Reptiles","Cats","Birds"};
        String[] ToFindElement= {"Cats-Persian-Adult Female","Dogs-Poodle-Male Puppy"};
        String[] ToRemove={"Adult Female"};

        //The actual tests
        //Addig the animals=>using the top menu
        for(String ToFind:ToFindElement) {
            int Numie = 1;
            //Wait
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[3]/tbody/tr/td[2]/font/a[" + Numie + "]")));
            for (String CATegorie : ToCheck) {
                String Cathy = driver.findElement(By.xpath("//table[3]/tbody/tr/td[2]/font/a[" + Numie + "]")).getText();
                System.out.println("We got:" + Cathy + " -- We wanted:" + CATegorie);
                Assert.assertTrue(Cathy.equals(CATegorie));
                //Als de diersoort gevonden is=>Doorgaan
                if (CATegorie.equals(ToFind.split("-")[0])) {
                    //Go to page & Test if on THE RIGHT page
                    driver.findElement(By.xpath("//table[3]/tbody/tr/td[2]/font/a[" + Numie + "]")).click();
                    System.out.println("We have found " + ToFind.split("-")[0]);
                    //Wait
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblPage\"]")));
                    String ThePage = driver.findElement(By.xpath("//*[@id=\"lblPage\"]")).getText();
                    Assert.assertTrue(ThePage.equals(ToFind.split("-")[0].toUpperCase()));
                    System.out.println("We Are on the right page: "+ ThePage);

                    //Via xpaths navigeren naar gewenste dier
                    //Using while to find in the animal category
                    //geprobeerd in externe functie te steken=>gewoon oproepen en meerdere keren herbruiken
                    int Numsie=2;
                    //Wait
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+Numsie+"]/td[2]/a")));
                    String Foundcat=null;
                    do{
                        Foundcat =driver.findElement(By.xpath("//tr["+Numsie+"]/td[2]/a")).getText();
                        if (Foundcat.equals(ToFind.split("-")[1])){
                            driver.findElement(By.xpath("//tr["+Numsie+"]/td[2]/a")).click();
                        }
                        Numsie++;
                    }while (!Foundcat.equals(ToFind.split("-")[1]));

                    //naar eigenlijke dier=> zou handiger zijn te kunnen zetten als functie die oproepbaar was
                    Numsie=2;
                    //Wait
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+Numsie+"]/td[2]/a")));
                    Foundcat=null;
                    do{
                        Foundcat =driver.findElement(By.xpath("//tr["+Numsie+"]/td[2]/a")).getText();
                        if (Foundcat.equals(ToFind.split("-")[2])){
                            driver.findElement(By.xpath("//tr["+Numsie+"]/td[2]/a")).click();
                        }
                        Numsie++;
                    }while (!Foundcat.equals(ToFind.split("-")[2]));

                    //Mogelijkheid elementen () van dier te plaatsen(Quality, price)
                    //Dier in mandje plaatsen
                    driver.findElement(By.xpath("//p[2]/a/img")).click();

                    //break the loop for finding the next element
                    break;


                }
                Numie++;
            }
        }
        //Nu naar de basket=>eerste dier eruit gooien (terug naar het aziel
        int Numsie=2;
        //Wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+Numsie+"]/td[3]/a")));
        String Foundcat=null;
        do{
            Foundcat =driver.findElement(By.xpath("//tr["+Numsie+"]/td[3]/a")).getText();
            if (Foundcat.equals(ToRemove[0])){
                driver.findElement(By.xpath("//tr["+Numsie+"]/td/input")).click();
            }
            Numsie++;
        }while (!Foundcat.equals(ToRemove[0]));



        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();

    }
}
