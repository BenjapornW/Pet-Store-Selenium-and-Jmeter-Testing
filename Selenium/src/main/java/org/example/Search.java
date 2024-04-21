package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Search {
    public void SearchTest() {}

    public static void main(String[] args) {
        // Setup Chrome Webdriver path
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        // Create instance of our driver
        WebDriver driver = new ChromeDriver();

        // Set up an implicit wait in case of slow connection / page load / etc.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /* Search */
        // 1. Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // 2. Locate the Search Field and type keyword
        WebElement searchField = driver.findElement(By.name("keyword"));
        searchField.clear();
        searchField.sendKeys("Koi");

        // 3. Locate the Search button and click
        driver.findElement(By.name("searchProducts")).click();

        // 4. Locate the Search Field and type non-existing product
        WebElement searchField2 = driver.findElement(By.name("keyword"));
        searchField2.clear();
        searchField2.sendKeys("Ant");

        // 5. Locate the Search button and click
        driver.findElement(By.name("searchProducts")).click();



    }
}
