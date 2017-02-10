package OefeningenSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 29/11/16.
 */
public class Doc4Ex4basicSnakeVersion01functions {
    /*The basics*/
    WebDriver driver;

    @Before
    public void setDriver() {
        //opening the window and go to the starting page
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
    }

    @After
    public void theCleanup() {
        /*-----------------------------------------------------------------------------------------
        cleanup=>closing the window
        -------------------------------------------------------------------------------------------*/
        System.out.println("cleaning actions! Have a nice day ;)");
        driver.quit();
    }


    /*The functions to use*/
    public void verifyTitleOfPage(String expectedtitle) {
        //To check if the title ot the page is correct

        String TheTitle=driver.getTitle();
        String TitleOfPage=expectedtitle;
        System.out.println(TheTitle);
        System.out.println(TheTitle.equals(TitleOfPage));
        Assert.assertEquals(TitleOfPage,TheTitle);
    }


    /*The test itself*/
    @Test
    public void theTest() {
        /* ---------------------------------------------------------------------------------
        the starting elements
        *-----------------------------------------------------------------------------------*/

        //Setting the implicitwait->click&wait->set it only 1x
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //setting start of explicit wait
        WebDriverWait wait= new WebDriverWait(driver,2);//blijkbaar nodig iedere keer!!!!!=>
        //Starting page=>a)navigate to the page
        driver.get("http://192.168.177.128/petshop");


        /*--------------------------------------------------------------------------------------
        Doing the tests
        ----------------------------------------------------------------------------------------*/
        //variables for doing the search
        String search="Reptile";
        String ToAnimal="Rattlesnake";
        String ToAnimalCategory="Venomless";
        //b)execute a search for "fish"

        //Ex2=>to uppercase
        driver.findElement(By.name("keywords")).sendKeys(search.toUpperCase());
        driver.findElement(By.linkText("SEARCH")).click();

        //c)Verifytitle

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));

        //outsource to function
        String expectedtitle="Search";
        verifyTitleOfPage(expectedtitle);


        //e)Open the details of the “AngelFish”. And verify that you are on the Items page of the “AngelFish”
        driver.findElement(By.linkText(ToAnimal)).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("title")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));

        String TheTitle = driver.findElement(By.cssSelector("span#productName")).getText();
        String TitleOfPage=ToAnimal;
        System.out.println("De gevonden titel is:"+TheTitle+" -Wat we willen:"+TitleOfPage);
        Assert.assertEquals(TitleOfPage,TheTitle);

        //f)Click on the link of the fish on the first row (name is “Large”).
        driver.findElement(By.linkText(ToAnimalCategory)).click();

        //g)	Verifies the values of this page: Title,Image(Use Xpath),Description,Price,Quantity
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
        //Title,Description,Price & Quantity check=>check text to what is expected


        //h)Click on the “Add to Cart” button. Use css to identify this button
        driver.findElement(By.cssSelector("img[alt=\"Add to Cart\"]")).click();

        //i)Assert by the title of the page if you are on the correct page.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
        TheTitle = driver.findElement(By.tagName("title")).getText();
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




    }
}
