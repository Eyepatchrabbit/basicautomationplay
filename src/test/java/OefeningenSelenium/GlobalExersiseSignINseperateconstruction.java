package OefeningenSelenium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 7/12/16.
 */
public class GlobalExersiseSignINseperateconstruction {
    public static void main(String[] args) throws IOException, ParseException {
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




        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/

        /*Startelements=> website to start, the file en some basic variables that need setting up
        +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop");

        //The file with the changable variables
        org.json.simple.parser.JSONParser parser=new org.json.simple.parser.JSONParser();
        JSONObject TheJSONFile = (JSONObject) parser.parse(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/Global exercise/docWebdriveGlobalS1.json"));
        //see if in right file
        System.out.print(TheJSONFile.get("NameFile"));

        System.out.println(" :Variables are read in - Here the test begins");



        /*(1)Register a new user
        * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //From main page move to page for registering
        driver.findElement(By.linkText("SIGN IN")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//We start with the clickandwait buzzkill
        driver.findElement(By.cssSelector("img[alt=\"Register Now\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));// We CRONOSmetreren het

        //Now on the page
        //a)fill in the elements needed=>join 2 elements from the jsonfile
        org.json.simple.JSONArray  AccountAndAddress= (JSONArray) TheJSONFile.get("My Account en Adress");

        //going over the elements we want to place
        int sizie = AccountAndAddress.size();
        String []Tofill;
        System.out.print("Account is "+sizie+" elements long - now typing information: ");
        for(int gegeven=0;gegeven<sizie-1;gegeven++){//hier geen nood om schoon te maken eerst
            Tofill=((String)AccountAndAddress.get(gegeven)).split(":");
            System.out.print(Tofill[1]+" ");
            if(gegeven<3){
                //hier geen toevoeging aan de id gedaan worden
                driver.findElement(By.id(Tofill[0])).sendKeys(Tofill[1]);

            }else{
                //Hier moet voor de Id aan te duiden nog "addr_" aan toe gevoegd worden
                driver.findElement(By.id("addr_"+Tofill[0])).sendKeys(Tofill[1]);
            }
        }

        //b)Now selecting the label elements
        //Select select = new Select(driver.findElement(By.id("addr_listState")));
        //select.selectByValue("New York");
        System.out.println("Now setting the selectables");
        org.json.simple.JSONArray  selectables= (JSONArray) TheJSONFile.get("Selectables");
        for(int gegeven=0;gegeven<4;gegeven++){//hier alleen de eerste 4 elementen moeten ingeven
            Tofill=((String)selectables.get(gegeven)).split(":");
            System.out.print(Tofill[1]+" ");
            if(gegeven<2) {//hier iets aan toevoegen voor id
                Select select = new Select(driver.findElement(By.id("addr_" + Tofill[0])));
                select.selectByVisibleText(Tofill[1]);
            }else {
                Select select = new Select(driver.findElement(By.id(Tofill[0])));
                select.selectByVisibleText(Tofill[1]);
            }
        }


        //c)Setting the checkboxes [V]
        System.out.println("Now setting the checkboxes at My Preferences");
        org.json.simple.JSONArray  clickables= (JSONArray) TheJSONFile.get("Preference Click");
        int numberofclicks=clickables.size();
        for (int clicknumber=0;clicknumber<numberofclicks;clicknumber++){
            driver.findElement(By.id((String) clickables.get(clicknumber))).click();
        }

        //d)click on the submit buttom
        driver.findElement(By.id("btnSubmit")).click();
        //waiting to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("font.menuBlack")));

        //extra=>if first time regestring (not retrying this code)=>need to log out to log in with DotNet
        if("SIGN OUT".equals(driver.findElement(By.xpath("//span/a")).getText())){
            driver.findElement(By.xpath("//span/a")).click();
        }


        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        //Log out if file not complete=>put// Afterwords!!!

        //Closing the window
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
