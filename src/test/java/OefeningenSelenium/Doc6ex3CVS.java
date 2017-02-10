package OefeningenSelenium;

import com.opencsv.CSVReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 6/12/16.
 */
public class Doc6ex3CVS {
    public static void main(String[] args) throws IOException {
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
        //reading the file and setting it to an iterator
        CSVReader reader = new CSVReader(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/WerkmapCSVstandaard4.csv"));
        List<String[]> AllArraysInFile=reader.readAll();
        Iterator<String[]> IteratorArraysInFile=AllArraysInFile.iterator();

        //try to iterate over the values on the homepage
        while (IteratorArraysInFile.hasNext()){
            String[] Arrayline=IteratorArraysInFile.next();
            System.out.print(Arrayline[0].toUpperCase()+" is expected -Given is: ");

            //Click on the link and wait till it loads properly
            driver.findElement(By.linkText(Arrayline[0])).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblPage")));//Use the title

            //See if we are on the right page
            System.out.println(driver.findElement(By.id("lblPage")).getText());
            int lengtArray= Arrayline.length;
            System.out.println("The length of the line "+Arrayline[0] +" is: "+lengtArray+" -It contains;");

            //Now see if the elements are on the page=>using the xpaths here (could also use the linktext i think?)
            for (int elementinlist=1;elementinlist<lengtArray;elementinlist++){
                System.out.print("The animal "+Arrayline[elementinlist]+" is expected - we found found: ");

                //now verification if it is on the page
                int numie=elementinlist+1;
                System.out.println(driver.findElement(By.xpath("//tr["+numie+"]/td[2]/a")).getText());
                Assert.assertTrue(Arrayline[elementinlist].equals(driver.findElement(By.xpath("//tr["+numie+"]/td[2]/a")).getText()));
            }

            //Going back to the homepage for the next round
            driver.navigate().back();


        }



         /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
