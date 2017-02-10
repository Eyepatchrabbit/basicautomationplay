package OefeningenSelenium;

import com.opencsv.CSVReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 6/12/16.
 */
public class Doc6ex4XML01 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
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
        //initialisation reading xml file
        File inputFile = new File("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/dierengegevensxmlvers02.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        //Get the root of the document& use .getNodeName() to convert it to a string
        String root = doc.getDocumentElement().getNodeName();
        System.out.println("The root of the used file is: <"+root+">");



        /*--------------------------------------------------------------------------------------
        Doing the tests
        +>List all the animals with his subcategory in the log. Use the forXml command. Use a xml file.
        ----------------------------------------------------------------------------------------*/
        //Can be changed=>Driver is with the starting elements
        String TheTagname="vars";
        String FirstAtribute="animal";
        String SecondAtribute="subcategory";
        //reading the file and setting it to an iterator=>see in startelements, Extra's

        //select the lines on basis of the tagnames& look at how long it is
        int TheLengthOfFile=doc.getElementsByTagName(TheTagname).getLength();
        System.out.println("The length of the file is: "+TheLengthOfFile);

        //Looking at what is expected and what is actually on the page
        for (int AtLine=0;AtLine<TheLengthOfFile;AtLine++) {
            //Uitzoeken of de diergroep er opstaat
            String TheAtributeWeWant1 = ((Element) doc.getElementsByTagName(TheTagname).item(AtLine)).getAttribute(FirstAtribute);
            System.out.print("1)/What we want to see on the page: " + TheAtributeWeWant1+" ");
            String TheAtributeWefind1=driver.findElement(By.xpath("//td[3]/a["+(AtLine+1)+"]")).getText();
            System.out.println("What we get:" + TheAtributeWefind1);
            Assert.assertTrue(TheAtributeWefind1.equals(TheAtributeWeWant1));

            //Nu kijken naar de beschrijving onder de diersoort
            String TheAtributeWeWant2 = ((Element) doc.getElementsByTagName(TheTagname).item(AtLine)).getAttribute(SecondAtribute);
            System.out.print("2)/The expected description: "+TheAtributeWeWant2.replaceAll(",", "").replaceAll("\\s+", "")+"\n");
            String TheAtributeWefind2=driver.findElement(By.xpath("//td[3]/font["+(AtLine+1)+"]")).getText();
            System.out.println("The descr. on the page: "+TheAtributeWefind2.replaceAll("\\s+", ""));
            Assert.assertTrue(TheAtributeWeWant2.replaceAll(",", "").replaceAll("\\s+", "").equals(TheAtributeWefind2.replaceAll("\\s+", "")));
        }





         /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
