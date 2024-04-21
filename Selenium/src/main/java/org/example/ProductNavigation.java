package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductNavigation {
    public void ProductNavigationTest() {}
    public static void main(String[] args) throws InterruptedException {

        // Setup Chrome Webdriver path
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        // Create instance of our driver
        WebDriver driver = new ChromeDriver();

        // Set up an implicit wait in case of slow connection / page load / etc.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));



        /* Product navigation */
        // 1. Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // 2. Locate the Fish text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[1]/img")).click();
        Thread.sleep(3000);

        // 3. Locate the Dogs text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[2]/img")).click();
        Thread.sleep(3000);

        // 4. Locate the Reptiles  text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[3]/img")).click();
        Thread.sleep(3000);

        // 5. Locate the Cats text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[4]/img")).click();
        Thread.sleep(3000);

        // 6. Locate the Birds text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[5]/img")).click();
        Thread.sleep(3000);

    }
}
