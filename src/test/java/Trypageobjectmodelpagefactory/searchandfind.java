package Trypageobjectmodelpagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class searchandfind {
    WebDriver driver;

    @FindBy (id="lst-ib")
    WebElement cleantextfield;

    @FindBy (id="gs_htif0")
    WebElement addsearch;

    @FindBy (name="btnG")
    WebElement searchbutton;


    //driver initialization
    public searchandfind(WebDriver driver){
        this.driver=driver;
        //DON'T FORGET THIS!!!!!!!!!!!!!!!!!!!!!!
        PageFactory.initElements(driver, this);
    }



    public void clearTextfield(){
        cleantextfield.clear();
    }

    public void addSearchstring(String searchstring) {
        addsearch.sendKeys(searchstring);
    }

    public  void doSearchForElement() {
        searchbutton.click();
    }




    //hoofdactie
    public  void placeSearchOnGoogle(String searchelement) {
        //make sure textfield is clear
        this.clearTextfield();

        //fill in information
        this.addSearchstring(searchelement);

        //click on the searchbutton
        this.doSearchForElement();

    }


}
