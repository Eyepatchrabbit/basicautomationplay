package Try;
import static org.junit.Assume.assumeTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
/**
 * Created by alexanderboffin on 13/12/16.
 */
public class trySafaridriver1 {
    private RemoteWebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void shouldBeAbleToPerformAGoogleSearch() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.titleIs("webdriver - Google Search"));
    }
}

