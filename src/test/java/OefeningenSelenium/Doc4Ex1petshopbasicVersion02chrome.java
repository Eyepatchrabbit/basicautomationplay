package OefeningenSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc4Ex1petshopbasicVersion02chrome {


    @Test
    public void GoogleSearch() {
        /* ---------------------------------------------------------------------------------
        the starting elements
        *-----------------------------------------------------------------------------------*/
        //opening the window and go to the starting page
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        //setting start of explicit wait
        WebDriverWait wait= new WebDriverWait(driver,9);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        //variables for doing the search
        String search="Fish";
        String ToAnimal="Angelfish";
        String ToAnimalCategory="Large";
        //Between pages to find
        String[] linksInPage={"Angelfish","Koi","Goldfish","Tiger Shark"};
        //Title,Description,Price & Quantity check=>check text to what is expected
        String TheBeast= ToAnimalCategory+" "+ToAnimal;
        String TheDescription="Saltwater fish from Australia";
        String ThePricie="$16.50";
        String TheQuantitie="10000";
        String[]TheXpaths={"/html/body/blockquote/table/tbody/tr/td[2]/span-"+TheBeast,"//*[@id=\"lblDescription\"]-"+TheDescription,"//*[@id=\"lblPrice\"]-"+ThePricie,"//*[@id=\"lblQty\"]-"+TheQuantitie};

        //b)execute a search for "fish"

        //Ex2=>to uppercase
        driver.findElement(By.name("keywords")).sendKeys(search.toUpperCase());
        driver.findElement(By.linkText("SEARCH")).click();

        //c)Verifytitle

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));


        String TheTitle=driver.getTitle();
        String TitleOfPage="Search";
        System.out.println(TheTitle);
        System.out.println(TheTitle.equals(TitleOfPage));
        Assert.assertEquals(TitleOfPage,TheTitle);

        //d)Assert the presense of the element link with “AngelFish” & other links

        for(String animal:linksInPage) {
            driver.findElement(By.linkText(animal));
            System.out.println(animal+"-link has been found");
        }

        //e)Open the details of the “AngelFish”. And verify that you are on the Items page of the “AngelFish”
        driver.findElement(By.linkText(ToAnimal)).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("title")));=> here not needed

        TheTitle = driver.findElement(By.cssSelector("span#productName")).getText();
        TitleOfPage=ToAnimal;
        System.out.println("De gevonden titel is:"+TheTitle+" -Wat we willen:"+TitleOfPage);
        Assert.assertEquals(TitleOfPage,TheTitle);

        //f)Click on the link of the fish on the first row (name is “Large”).
        driver.findElement(By.linkText(ToAnimalCategory)).click();

        //g)	Verifies the values of this page: Title,Image(Use Xpath),Description,Price,Quantity
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
          for (String el:TheXpaths) {
            TheTitle = driver.findElement(xpath(el.split("-")[0])).getText();
            System.out.println(TheTitle);
            Assert.assertEquals(TheTitle, el.split("-")[1]);
            System.out.println(TheTitle +" of path "+el.split("-")[0]+" is equal to "+el.split("-")[1]);
        }
        //nu de foto van de vis
        String alepistole=driver.findElement(By.xpath("/html/body/blockquote/table/tbody/tr/td[1]/span/img")).getAttribute("src");
        System.out.println(alepistole);
        Assert.assertTrue(alepistole.contains("jpg"));

        //h)Click on the “Add to Cart” button. Use css to identify this button
        driver.findElement(By.cssSelector("img[alt=\"Add to Cart\"]")).click();

        //i)Assert by the title of the page if you are on the correct page.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
        TheTitle = driver.findElement(By.cssSelector("span.title")).getText();
        TitleOfPage="Shopping Cart";
        System.out.println("De gevonden titel is:"+TheTitle+" -Wat we willen:"+TitleOfPage);
        Assert.assertEquals(TitleOfPage,TheTitle);


        //exercise 3: calculations at the shopping cart
        //getting the values of the site
        String PriceItem=driver.findElement(By.cssSelector("td.num")).getText();
        String qualityinbasket=driver.findElement(By.id("cart_txtQty_0")).getAttribute("value");
        String totalRow=driver.findElement(By.xpath("//form[@id='frmCart']/table/tbody/tr/td/p/table/tbody/tr/td/table/tbody/tr[2]/td[7]")).getText();
        System.out.println(PriceItem+" "+ qualityinbasket+" "+totalRow);
        //conversion to numbers
        Double numie1=Double.parseDouble(PriceItem.substring(1));
        int Qnumie=Integer.parseInt(qualityinbasket);
        Double numie2=Double.parseDouble(totalRow.substring(1));
        System.out.println(numie1+"x"+ Qnumie+"="+numie2+" Have calculated:"+numie1*Qnumie);
        Assert.assertTrue(numie1*Qnumie==numie2);


        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();

    }
}
