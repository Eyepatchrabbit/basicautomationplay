package tryfurtherselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexanderboffin on 6/01/17.
 */
public class TryingPopUpWindowGuru {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        WebDriver driver = new FirefoxDriver();

        //setting a waits
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,10);


        //Launching the site.
        driver.get("http://demo.guru99.com/popup.php");
        driver.manage().window().maximize();

        //see what is the main windox
        String MainWindow=driver.getWindowHandle();
        System.out.println("The mainwindow is: "+MainWindow);

        //clicking to open the new window
        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //String secondWindow=driver.getWindowHandle();
        //System.out.println("The window now after clicking is: "+secondWindow);


        // To handle all new opened window.
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        int ofstring=s1.size();
        System.out.println("number of items in windowhandles: "+ofstring);

        while(i1.hasNext())
        {
            String ChildWindow=i1.next();

            System.out.println("The window in list is: "+ChildWindow);

            if(MainWindow.equalsIgnoreCase(ChildWindow)){
                System.out.println("\tThis is the mainwindow!");
            }

            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {

                // Switching to Child window
                driver.switchTo().window(ChildWindow);

                wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("emailid"))));

                driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");

                //driver.findElement(By.name("btnLogin")).click();

                // Closing the Child Window.
                driver.close();
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);

        driver.quit();
    }
    }

