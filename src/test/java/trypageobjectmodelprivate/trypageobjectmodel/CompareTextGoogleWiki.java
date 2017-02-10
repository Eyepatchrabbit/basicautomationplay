package trypageobjectmodelprivate.trypageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 12/01/17.
 */
public class CompareTextGoogleWiki {
    WebDriver driver;

    By wikitextongoogle=By.cssSelector("span.st");
    By buttonwikipageongoogle=By.partialLinkText("Wikipedia");
    By firsttextonwiki=By.xpath("//body/div[3]/div[3]/div[4]/p[1]");

    String splitinggoogle="\\.\\.\\.";


    public CompareTextGoogleWiki(WebDriver driver){
        this.driver=driver;
    }


    private String textOnGoogle(){
        String textwiki = driver.findElement(wikitextongoogle).getText();
        String ongoogle = textwiki.split(splitinggoogle)[0];
        return ongoogle;
    }

    private void toWikipage(){
        driver.findElement(buttonwikipageongoogle).click();
    }

    private String textOnWikipage(){
        String firstwikitext = driver.findElement(firsttextonwiki).getText();
        return firstwikitext;
    }

    private boolean compareTexts(String textonwiki, String partialongoogle){
        Assert.assertTrue(textonwiki.contains(partialongoogle));
        return true;
    }


    private void backToGoogle(){
        driver.navigate().back();
    }



    public void compateGoogleAndWikiTexts(){
        //do this if we heve done a search on google
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,10);

        String googletext =this.textOnGoogle();

        this.toWikipage();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firsttextonwiki));

        String wikitext=this.textOnWikipage();

        boolean equals=this.compareTexts(wikitext,googletext);

        backToGoogle();

        //System.out.println("did it go true?");

    }


}
