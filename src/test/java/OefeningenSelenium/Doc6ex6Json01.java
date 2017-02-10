package OefeningenSelenium;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 6/12/16.
 */
public class Doc6ex6Json01 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {
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
        driver.get("http://192.168.177.128/petshop");

        //EXTRA's++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //initialisation reading from JSON file
        //read the file itself
        org.json.simple.parser.JSONParser parser=new org.json.simple.parser.JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/doc6ex6d.json"));

        //Are we in the right file:
        System.out.println(obj.get("Name"));

        /*--------------------------------------------------------------------------------------
        Doing the tests
        +>List all the animals with his subcategory in the log. Use the forXml command. Use a xml file.
        ----------------------------------------------------------------------------------------*/
        //selecting from the file what I need=>the special array with selectables
        org.json.simple.JSONArray arrSP= (org.json.simple.JSONArray) obj.get("TheHomepage");

        //Looking at how manny elements are in it
        int TheSizeOfFile=arrSP.size();
        System.out.println(TheSizeOfFile);

        //now setting up the loop like in Doc6ex4XML
        JSONObject objOfArray;
        for(int Theline=0;Theline<TheSizeOfFile;Theline++){
            objOfArray = (JSONObject) arrSP.get(Theline);

            //eerste element controleren
            String TheAnimalClas=(String) objOfArray.get("animal");
            System.out.print(TheAnimalClas+" is what we want and we get: ");
            String TheAtributeWefind1=driver.findElement(By.xpath("//td[3]/a["+(Theline+1)+"]")).getText();
            System.out.println(TheAtributeWefind1);

            Assert.assertTrue(TheAnimalClas.equals(TheAtributeWefind1.toUpperCase()));

            //het tweede element controleren
            String TheSubcategory=(String) objOfArray.get("subcategory");
            System.out.print(TheSubcategory+" is what we want. And we get: ");
            String TheAtributeWefind2=driver.findElement(By.xpath("//td[3]/font["+(Theline+1)+"]")).getText();
            System.out.println(TheAtributeWefind2.replaceAll("\\s+", ""));

            Assert.assertTrue(TheSubcategory.replaceAll(",", "").replaceAll("\\s+", "").equals(TheAtributeWefind2.replaceAll("\\s+", "")));
        }



         /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
