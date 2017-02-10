package Trypageobjectmodelpagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class CompareTextGoogleWiki {
    WebDriver driver;

    @FindBy (css="span.st")
    WebElement wikitextongoogle;

    @FindBy(partialLinkText="Wikipedia")
    WebElement buttonwikipageongoogle;

    @FindBy (xpath="//body/div[3]/div[3]/div[4]/p[1]")
    WebElement firsttextonwiki;

    String splitinggoogle="\\.\\.\\.";


    public CompareTextGoogleWiki(WebDriver driver){
        this.driver=driver;
    }


    public String textOnGoogle(){
        String textwiki = wikitextongoogle.getText();
        String ongoogle = textwiki.split(splitinggoogle)[0];
        return ongoogle;
    }

    public void toWikipage(){
        buttonwikipageongoogle.click();
    }

    public String textOnWikipage(){
        String firstwikitext = firsttextonwiki.getText();
        return firstwikitext;
    }

    public boolean compareTexts(String textonwiki, String partialongoogle){
        Assert.assertTrue(textonwiki.contains(partialongoogle));
        return true;
    }


    public void backToGoogle(){
        driver.navigate().back();
    }



    public void compateGoogleAndWikiTexts(){
        //do this if we heve done a search on google
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,10);

        String googletext =this.textOnGoogle();

        this.toWikipage();

        wait.until(ExpectedConditions.visibilityOfElementLocated((By) firsttextonwiki));

        String wikitext=this.textOnWikipage();

        boolean equals=this.compareTexts(wikitext,googletext);

        backToGoogle();

        //System.out.println("did it go true?");

    }


}
