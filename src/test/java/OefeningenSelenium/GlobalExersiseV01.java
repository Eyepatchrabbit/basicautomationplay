package OefeningenSelenium;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 7/12/16.
 */
public class GlobalExersiseV01 {
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
        WebDriverWait wait= new WebDriverWait(driver,5);//blijkbaar nodig iedere keer!!!!!




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

        //EXTRA=>to check the quantity on the end

        //=>meant to automate the quantity search=>point 9 (checking after placed order)
        //Animal+quantity
        List<String> AnimalQuantityStart=null;


        //Need to know the size of the different searchables=>initiate it here=>can be that search is skipped. Alo reuse probably saves memory
        int Thesize;
        String searchables;
        String[] Searchableslist;
        int length;

        /*(1)Register a new user
        * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


        //Don't forget to log out if it's the first time registering!!!!!=>otherwise cant go further



        /*(2)Log in with the user DotNet and password Dotnet
        * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //Click on the log in
        driver.findElement(By.linkText("SIGN IN")).click();

        //Wait for propper loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//Are you a new user?

        //Fill in ID And password
        String[] TheFields={"txtUserId","txtPassword"};//The fields that need to be filled
        org.json.simple.JSONArray IDAPW= (org.json.simple.JSONArray)  TheJSONFile.get("LogInIdAndPassw");
        for (int Numie=0;Numie<2;Numie++){
            driver.findElement(By.id(TheFields[Numie])).clear();//first making sure it is empty
            //String type= (String) IDAPW.get(Numie);
            //driver.findElement(By.id(TheFields[Numie])).sendKeys(type);
            System.out.print((String) IDAPW.get(Numie));
            driver.findElement(By.id(TheFields[Numie])).sendKeys((String) IDAPW.get(Numie));

        }

        //Log in=>click "submit" buttom
        driver.findElement(By.id("btnSubmit")).click();

        System.out.println(" :Is entered - We are now logged in");

        //Wait for loading page before next step:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//My Account


        /*(3)Add some animals to your chart by searching for a pet
        *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
        //Getting the needed elements from the file
        System.out.println("Are at the search module");
        org.json.simple.JSONArray Searchfor= (org.json.simple.JSONArray)  TheJSONFile.get("Search for");
        org.json.simple.JSONArray SearchGoTO= (org.json.simple.JSONArray)  TheJSONFile.get("Search GoTO");

        //Look how manny times we need to add an animal using this method
        Thesize=Searchfor.size();
        System.out.println("We need to search for "+Thesize+" Animals to place them in the basket");

        //Now can make a loop for it=>can be that we want to put several animals in the basket by the loop
        for (int Numie=0;Numie<Thesize;Numie++){
            //Turning the animallist into a string
            searchables=(String) Searchfor.get(Numie);
            Searchableslist=searchables.split(",");

            //Now see how manny times we search before finding the animal
            length=Searchableslist.length;
            System.out.print("now we are looking "+length+" times before finding the needed element. The elements searched are: ");

            for(int numie=0;numie<length;numie++) {
                driver.findElement(By.name("keywords")).clear();
                System.out.print(Searchableslist[numie]+" ");
                driver.findElement(By.name("keywords")).sendKeys(Searchableslist[numie]);

                //doing the search
                driver.findElement(By.linkText("SEARCH")).click();

                //waiting for propper loading before moving on
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//search Results
            }
            System.out.println("Done with searching - We are moving on towards the animal");

            //Now we should be at the search where the animal is we need to put in the basket+>use elements of SearchToGo to navigate to the right animal
            String ToAnimalSearch=(String) SearchGoTO.get(Numie);
            String[] ToTheAlnimal=ToAnimalSearch.split(",");

            //It is always two steps from the searchengine to the animalpage
            for (String ToAnimal:ToTheAlnimal){
                driver.findElement(By.linkText(ToAnimal)).click();//Ik heb hier voor de "luie optie gekozen=> gewoon op de links klikken

                //Waiting for the page to load
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//search Results
            }

            //Are on the animal page now
            //Need the quantity for on the end (checking if the quantity goes down after buying a few)=>

            //Can be that no animal needs to be selected=>can later make an if function for it here


            // =>select it for in the basket
            driver.findElement(By.cssSelector("img[alt=\"Add to Cart\"]")).click();
            System.out.println("Animal in basket");
            //And waiting again till properly loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//search Results

        }


        /*(4a)Add some animals to your chart by using the navigation panel with images on the home screen on the left,
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        System.out.println("Starting with Navigation");
        org.json.simple.JSONArray Navogatefor= (org.json.simple.JSONArray)  TheJSONFile.get("Navigate for");
        org.json.simple.JSONArray NavigateGoTO= (org.json.simple.JSONArray)  TheJSONFile.get("Natvigate GoTo");

        //Look how manny times we need to add an animal using this method
        Thesize = NavigateGoTO.size();
        System.out.println("We need to navigate to "+Thesize+" Animals to put them in the basket");

        //Going to the homepage for implementing the search like asked
        driver.findElement(By.cssSelector("a > img")).click();

        //Don't forget to wait for element to load properly before moving on=>in debug time enough, when doing it for real its to fast
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.catLink")));//on home page no span.title

        //Using a loop for doing the different searches+>each to add an animal to the basket or just stop after searching
        //choosing the second array because it can be we have a person that doesn't need to search first => goes direct to the animal (second element)
        for (int Numbs=0;Numbs<Thesize;Numbs++){
            //looking at what we need to go throug
            searchables=(String) Navogatefor.get(Numbs);
            Searchableslist=searchables.split(",");

            //Now see how manny times we navigate to a link and back before finding the animal we want
            length=Searchableslist.length;
            System.out.print("now we are looking "+length+" times before finding the needed element. The elements navigated are: ");

            //This is to simulate somene searching the main menu
            for (int Nubbs=0;Nubbs<length;Nubbs++){
                driver.findElement(By.linkText(Searchableslist[Nubbs])).click();;
                System.out.print(Searchableslist[Nubbs]+" ");

                //back to the homepage
                driver.navigate().back();;

                //And waiting again till properly loaded again
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.catLink")));//on home page no span.title
            }
            System.out.println(" Navigating throug unwanted links complete - moving on");
            //now the last link=>then we move to the needed animal
            searchables=(String) NavigateGoTO.get(Numbs);
            Searchableslist=searchables.split(",");
            for(String Search:Searchableslist){
                driver.findElement(By.linkText(Search)).click();

                //And waiting again till properly loaded again
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("font.menuBlack")));//search Results
            }

            //load the animal into the basket
            driver.findElement(By.cssSelector("img[alt=\"Add to Cart\"]")).click();
            System.out.println("Animal in basket by navgation");

            //And waiting again till properly loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//search Results
        }

        //Can place a split here=>can be that the person doesn't want to buy what he put in the basket=>would logg out then


        /*(4b)checking the basket
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //now we should have added an animal in the basked before this=> We go automatically to the page
        //To make it somewhat easier on myself, I'm going to use the JSON FILE
        //Can probably automate by selecting and storing elements from the pages before tutting animal in basket->will try to do this later
        System.out.println("Are at the basket");

        //getting elements out an array with selectables
        org.json.simple.JSONArray InBasket= (org.json.simple.JSONArray) TheJSONFile.get("In Basket");

        //The Total Sum (know float and double aren't recomended, but didn't know what else to use
        double ToPayCalculated=0;

        //Can iterate for each element we have in the basket & make a subtotal
        Thesize=InBasket.size();//see how manny elements we need to check
        System.out.println("Need to check the data of "+Thesize+" elements;");

        for(int Numie=0;Numie<Thesize;Numie++){
            //Going to the richt level=>variables to check
            JSONObject DataOfAnimal=(JSONObject)InBasket.get(Numie);

            //getting the needed data from the page=>using X-path
            String IDp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[2]")).getText();
            String Productp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[3]/a")).getText();
            String Pricep=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[5]")).getText().substring(1);
            String Quantityp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[6]/input")).getAttribute("value");
            System.out.print("The data of iteration "+Numie+" is on the page: "+IDp+" "+Productp+" "+Pricep+" "+Quantityp);


        }


        //Check the total calculated with what is on the page=>at the end of the checking


        /*(5 & 6)checkout and to the payment information page
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/





        /*(7)Filling in the payment information
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/




        /*(8)Check payment screen=>reuse elements of (1)
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/



        /*(9)The terror: Checking the quality=>Really need to rethink my approach here!!!!
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/



        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        //Log out if file not complete=>just good form
        driver.findElement(By.linkText("SIGN OUT")).click();

        //Closing the window
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
