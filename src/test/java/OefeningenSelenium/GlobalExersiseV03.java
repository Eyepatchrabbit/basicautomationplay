package OefeningenSelenium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
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
public class GlobalExersiseV03 {
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
        JSONObject TheJSONFile = (JSONObject) parser.parse(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/Global exercise/docWebdriveGlobalS1a.json"));
        //see if in right file
        System.out.print(TheJSONFile.get("NameFile"));

        System.out.println(" :Variables are read in - Here the test begins");

        //EXTRA=>to check the quantity on the end

        //=>meant to automate the quantity search=>point 9 (checking after placed order)
        //Animal+quantity
        List<String> AnimalQuantityStart=null;


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
                driver.findElement(By.id("addr_txt"+Tofill[0])).sendKeys(Tofill[1]);
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
            driver.findElement(By.xpath("font.menuBlack")).click();
            System.out.println("Hello new user");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("SIGN IN")));
        }
        System.out.println("Moving on from registration");


        /*(2)Log in with the user DotNet and password Dotnet
        * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //Click on the log in
        driver.findElement(By.linkText("SIGN IN")).click();
        System.out.println("Now logging in as DotNet");

        //Wait for propper loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));//Are you a new user?

        //Fill in ID And password
        String[] TheFields={"txtUserId","txtPassword"};//The fields that need to be filled
        org.json.simple.JSONArray IDAPW= (org.json.simple.JSONArray)  TheJSONFile.get("LogInIdAndPassw");
        for (int Numie=0;Numie<2;Numie++){
            driver.findElement(By.id(TheFields[Numie])).clear();//first making sure it is empty
            System.out.println((String) IDAPW.get(Numie));
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
        int Thesize=Searchfor.size();
        System.out.println("We need to search for "+Thesize+" Animals to place them in the basket");

        //Now can make a loop for it=>can be that we want to put several animals in the basket by the loop
        for (int Numie=0;Numie<Thesize;Numie++){
            //Turning the animallist into a string
            String searchables=(String) Searchfor.get(Numie);
            String[]Searchableslist=searchables.split(",");

            //Now see how manny times we search before finding the animal
            int length=Searchableslist.length;
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
        int Thesizenav = NavigateGoTO.size();
        System.out.println("We need to navigate to "+Thesizenav+" Animals to put them in the basket");

        //Going to the homepage for implementing the search like asked
        driver.findElement(By.xpath("//a/img")).click();

        //Don't forget to wait for element to load properly before moving on=>in debug time enough, when doing it for real its to fast
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[2]/img[2]")));//on home page no span.title

        //Using a loop for doing the different searches+>each to add an animal to the basket or just stop after searching
        //choosing the second array because it can be we have a person that doesn't need to search first => goes direct to the animal (second element)
        for (int Numbs=0;Numbs<Thesizenav;Numbs++){
            //looking at what we need to go throug
            String NAVigatable=(String) Navogatefor.get(Numbs);
            String[]NAVigatablelist=NAVigatable.split(",");

            //Now see how manny times we navigate to a link and back before finding the animal we want
            int lengthN=NAVigatablelist.length;
            System.out.print("now we are looking "+lengthN+" times before finding the needed element. The elements navigated are: ");

            //This is to simulate somene searching the main menu
            for (int Nubbs=0;Nubbs<lengthN;Nubbs++){
                driver.findElement(By.linkText(NAVigatablelist[Nubbs])).click();;
                System.out.print(NAVigatablelist[Nubbs]+" ");

                //WAit to load properly
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblPage")));

                //back to the homepage
                //driver.navigate().back();//=>something wrong (goes back sometimes to first animal=>dubbleclick back)
                //Try this way=> .back() sometimes clicks 2x ???
                driver.findElement(By.xpath("//a/img")).click();

                //And waiting again till properly loaded again
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[2]/img[2]")));//on home page no span.title
            }
            System.out.println(" Navigating throug unwanted links complete - moving on");
            //now the last link=>then we move to the needed animal
            String NAVigatableGO=(String) NavigateGoTO.get(Numbs);
            String[] NAVigatableGOlist=NAVigatableGO.split(",");
            for(String Search:NAVigatableGOlist){
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
        int ThesizeB=InBasket.size();//see how manny elements we need to check
        System.out.println("Need to check the data of "+ThesizeB+" elements;");

        for(int Numie=0;Numie<ThesizeB;Numie++){
            //Going to the richt level=>variables to check
            JSONObject DataOfAnimal=(JSONObject)InBasket.get(Numie);

            //getting the needed data from the page=>using X-path
            String IDp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[2]")).getText();
            String Productp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[3]/a")).getText();
            String Pricep=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[5]")).getText().substring(1);
            String Quantityp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[6]/input")).getAttribute("value");
            System.out.print("The data of iteration "+Numie+" is on the page: "+IDp+" "+Productp+" "+Pricep+" "+Quantityp+" ");

            //Check if the values match
            Assert.assertTrue(IDp.equals(DataOfAnimal.get("ID")));
            Assert.assertTrue(Productp.equals(DataOfAnimal.get("Product")));
            Assert.assertTrue(Pricep.equals(DataOfAnimal.get("Price")));
            Assert.assertTrue(Quantityp.equals(DataOfAnimal.get("Quality")));
            System.out.println("Iteretation "+Numie+" Goth trough");

            //Now the total sum
            ToPayCalculated=ToPayCalculated+(Float.parseFloat(Pricep)*Float.parseFloat(Quantityp));
            System.out.println("Price now: "+ToPayCalculated);


        }

        //Check the total calculated with what is on the page=>at the end of the checking
        String ToPayOnSite =driver.findElement(By.cssSelector("tr.gridFoot > td.num")).getText().split("\\$")[1];//don't forget to escape special characters
        Assert.assertTrue(ToPayCalculated==Float.parseFloat(ToPayOnSite));
        System.out.println("The payment is correct");

        /*(5 & 6)checkout and to the payment information page
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //(5)=>proceed to checkout
        driver.findElement(By.cssSelector("img[alt=\"Proceed to Checkout\"]")).click();

        //The white rabbit of wonderland
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));

        //(6)Click on the continue button
        driver.findElement(By.cssSelector("img[alt=\"Continue\"]")).click();

        //The white rabbit of Underland
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));


        /*(7)Filling in the payment information=>reuse elements form (1)
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //First write into the textfields=>reused code from (1)a)
        System.out.print("Are filling in the payment - now typing information: ");
        for(int gegeven=3;gegeven<sizie;gegeven++){
            Tofill=((String)AccountAndAddress.get(gegeven)).split(":");
            System.out.print(Tofill[1]+" ");
            if(gegeven==sizie-1){
                //hier geen toevoeging aan de id gedaan worden
                driver.findElement(By.id(Tofill[0])).clear();//here remove text already present
                driver.findElement(By.id(Tofill[0])).sendKeys(Tofill[1]);

            }else{
                //Hier moet voor de Id aan te duiden nog "billAddr_" aan toe gevoegd worden
                driver.findElement(By.id("billAddr_txt"+Tofill[0])).clear();//here remove text already present
                driver.findElement(By.id("billAddr_txt"+Tofill[0])).sendKeys(Tofill[1]);
            }
        }


        //Second:Set the selectables=>also reused
        System.out.println("Filled in the payment - Now setting the selectables: ");
        for(int gegeven=0;gegeven<7;gegeven++){//hier alleen de eerste 4 elementen moeten ingeven
            Tofill=((String)selectables.get(gegeven)).split(":");
            System.out.print(Tofill[1]+" ");
            if(gegeven<2) {//hier iets aan toevoegen voor id
                Select select = new Select(driver.findElement(By.id("billAddr_" + Tofill[0])));
                select.selectByVisibleText(Tofill[1]);
            }else if(gegeven<4){//Go over the preferences of the registerpage (aren't used  here)
                continue;
            }else {//laatste 3 elementen kunnen hier zo ingevuld worden
                Select select = new Select(driver.findElement(By.id(Tofill[0])));
                select.selectByVisibleText(Tofill[1]);
            }
        }
        System.out.println("Have selected the nessecary elements in the payment -Moving on");
        //I stay Away from from the clickable element "Ship to billing address"=>gives fault ik checked off



        /*(8a)Check payment screen=>reuse elements of (1)
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //First move to the next page (!!ClickAndWait)
        driver.findElement(By.id("btnContinue")).click();
        System.out.println("Now at the confirmation site -");

        //wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));

        //Need to check 2x=>1e=Billing Adress 2e=Shipping Address
        String[] prefixes={"staticAddressBilling_lbl","staticAddressShipping_lbl"};
        String[] idtouse={"FirstName","LastName","Adr1","Adr2","City","PostalCode"};

        for (String useprefix:prefixes){
            int elementinarray=3;//Starten met het 4e element in de "My Account en Adress"
            System.out.println("checking "+useprefix+" -");
            for (String idused:idtouse){
                //here try to see if data is acurately displayed on the site
                String check=((String)AccountAndAddress.get(elementinarray)).split(":")[1];
                String tocheckonpage=driver.findElement(By.id(useprefix+idused)).getText();
                System.out.print(check+"="+tocheckonpage+ " -");
                Assert.assertTrue(check.equals(tocheckonpage));
                //Don't forget to increase the elementinarray=>here tried a foreach loop to make it easier. Took some time to see what was wrong
                elementinarray=elementinarray+1;
            }
            //Also check the state ,"State"=>in different array in file
            String tocheckonpage=driver.findElement(By.id(useprefix+"State")).getText();
            String thestate=((String)selectables.get(0)).split(":")[1].substring(0,2);
            Assert.assertTrue(tocheckonpage.equals(thestate));//checking out the data
            System.out.print(thestate+" "+tocheckonpage);
        }

        /*(8b)Confirm
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //Press the "Continue botton" to confirm
        driver.findElement(By.cssSelector("img[alt=\"Continue\"]")).click();

        //Wait for propper loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));


        /*(9)Check details if the order is complete=>redo the elements of (8a) and (4b)=>need to realy find a way of making them into functions (would be much easier)
        * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        //I'm going to leave the Date, order ID and status for what they are(last 2 I don't know what to do with them).
        //(A)Checking the new elements displayed=>User ID, cardtype, cardmonth, cardYear, cardnummer
        System.out.println("Now confirming the completed order");

        //check if Id matches
        Assert.assertTrue("DotNet".equals(driver.findElement(By.id("lblUserId")).getText()));
        System.out.print("have the right User ID");

        //check the card number+>in array "My Account en Adress"
        String cardnr=driver.findElement(By.id("lblCardNumber")).getText();
        System.out.print(" The cardnumber:"+cardnr+"="+((String)AccountAndAddress.get(10)).split(":")[1]);
        Assert.assertTrue(cardnr.equals(((String)AccountAndAddress.get(10)).split(":")[1]));

        //Checking ou the other payment information=>in a different array "Selectables" vanaf element 4
        //make string we can loop throug=> '/' as a seperator of the information
        String tocheckpaymentinfo=driver.findElement(By.id("lblCardType")).getText()+"/"+driver.findElement(By.id("lblCardExpiration")).getText();
        for(int gegeven=4;gegeven<7;gegeven++){//hier alleen de eerste 4 elementen moeten ingeven
            Tofill=((String)selectables.get(gegeven)).split(":");
            System.out.print(Tofill[1]+" "+tocheckpaymentinfo.split("/")[gegeven-4]);
            Assert.assertTrue(Tofill[1].equals(tocheckpaymentinfo.split("/")[gegeven-4]));
        }
        System.out.println("have checked ID, cardtype, cardmonth, cardYear, cardnummer: are correct");

        //(B)Checking out the shipping and billing adress
        //=>reuse(8a)->just modify the selectors of where to find elements on the page
        prefixes= new String[]{"statAddrBill_lbl", "statAddrShip_lbl"};//only had to change this.Rest code unchanged
        idtouse= new String[]{"FirstName", "LastName", "Adr1", "Adr2", "City", "PostalCode"};

        for (String useprefix:prefixes){
            int elementinarray=3;//Starten met het 4e element in de "My Account en Adress"
            System.out.println("checking "+useprefix+" -");
            for (String idused:idtouse){
                //here try to see if data is acurately displayed on the site
                String check=((String)AccountAndAddress.get(elementinarray)).split(":")[1];
                String tocheckonpage=driver.findElement(By.id(useprefix+idused)).getText();
                System.out.print(check+"="+tocheckonpage+ " -");
                Assert.assertTrue(check.equals(tocheckonpage));
                //Don't forget to increase the elementinarray=>here tried a foreach loop to make it easier. Took some time to see what was wrong
                elementinarray=elementinarray+1;
            }
            //Also check the state ,"State"=>in different array in file
            String tocheckonpage=driver.findElement(By.id(useprefix+"State")).getText();
            String thestate=((String)selectables.get(0)).split(":")[1].substring(0,2);
            Assert.assertTrue(tocheckonpage.equals(thestate));//checking out the data
            System.out.print(thestate+" "+tocheckonpage);
        }
        System.out.println("have checked the shipping and billing adress - are correct");


        //(C)Checking out the animals bought
        //=>reuse(4b)->just modify the selectors of where to find elements on the page
        ToPayCalculated=0;

        //Can iterate for each element we have in the basket & make a subtotal
        System.out.println("Need to check the data of "+ThesizeB+" animals bought;");

        for(int Numie=0;Numie<ThesizeB;Numie++){
            //Going to the richt level=>variables to check
            JSONObject DataOfAnimal=(JSONObject)InBasket.get(Numie);

            //getting the needed data from the page=>using X-path//Here just modified where to search
            String IDp=driver.findElement(By.xpath("//tr[8]/td[2]/table/tbody/tr["+(Numie+2)+"]/td")).getText();
            String Productp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[2]/a")).getText();
            String Pricep=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[3]")).getText().substring(1);
            String Quantityp=driver.findElement(By.xpath("//tr["+(Numie+2)+"]/td[4]")).getText();//from value to text here!!!!!
            System.out.print("The data of iteration "+Numie+" is on the page: "+IDp+" "+Productp+" "+Pricep+" "+Quantityp+" ");

            //Check if the values match
            Assert.assertTrue(IDp.equals(DataOfAnimal.get("ID")));
            Assert.assertTrue(Productp.equals(DataOfAnimal.get("Product")));
            Assert.assertTrue(Pricep.equals(DataOfAnimal.get("Price")));
            Assert.assertTrue(Quantityp.equals(DataOfAnimal.get("Quality")));
            System.out.println("Iteretation "+Numie+" Goth trough");

            //Now the total sum
            ToPayCalculated=ToPayCalculated+(Float.parseFloat(Pricep)*Float.parseFloat(Quantityp));
            System.out.println("Price now: "+ToPayCalculated);


        }

        //Check the total calculated with what is on the page=>at the end of the checking//Used a different locator than in (4a)
        ToPayOnSite =driver.findElement(By.id("lblOrderTotal")).getText().split("\\$")[1];//don't forget to escape special characters
        Assert.assertTrue(ToPayCalculated==Float.parseFloat(ToPayOnSite));
        System.out.println("The payment is correct and animal data is justly represented");


        /*------------------------------------------------------------------------------------------------------------------------------------------
        cleanup=>closing the window
        --------------------------------------------------------------------------------------------------------------------------------------------*/
        //Log out if file not complete=>just good form
        driver.findElement(By.linkText("SIGN OUT")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));

        //Closing the window
        System.out.println("\n cleaning actions! Have a nice day ;)");
        driver.quit();
    }
}
