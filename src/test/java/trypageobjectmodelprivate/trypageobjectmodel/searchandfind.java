package trypageobjectmodelprivate.trypageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class searchandfind {
    WebDriver driver;

    By cleantextfield=By.id("lst-ib");
    By addsearch= By.id("gs_htif0");
    By searchbutton=By.name("btnG");


    //driver initialization
    public searchandfind(WebDriver driver){
        this.driver=driver;
    }



    private void clearTextfield(){
        driver.findElement(cleantextfield).clear();
    }

    private void addSearchstring(String searchstring) {
        driver.findElement(addsearch).sendKeys(searchstring);
    }

    private void doSearchForElement() {
        driver.findElement(searchbutton).click();
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
