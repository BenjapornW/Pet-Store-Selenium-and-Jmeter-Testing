package org.example;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@SpiraTestConfiguration(
        url = "https://rmit.spiraservice.net",
        login = "s3961136",
        rssToken = "{C92814C3-73EF-47B2-9007-4547F84D90B4}",
        projectId = 122
)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {

    private static ChromeDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 3971)
    public void testSearch()
    {
        // Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // Search for "Koi"
        WebElement searchField = driver.findElement(By.name("keyword"));
        searchField.clear();
        searchField.sendKeys("Koi");
        driver.findElement(By.name("searchProducts")).click();

        // Verify some result for "Koi"
        assertTrue(driver.getPageSource().contains("Koi"));
        assertTrue(driver.getPageSource().contains("FI-FW-01"));
    }

    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 3994)
    public void testSearch_NonExistingProduct() throws InterruptedException {

        // Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // Search for "Ant" - non-existing product
        WebElement searchField = driver.findElement(By.name("keyword"));
        searchField.clear();
        searchField.sendKeys("Ant");
        driver.findElement(By.name("searchProducts")).click();

        // Wait for some time or use Explicit Wait for better synchronization
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("keyword")));

        // Re-find the search field and clear
        searchField = driver.findElement(By.name("keyword"));
        searchField.clear();

        // Verify some result for "Ant"
        assertFalse(driver.getPageSource().contains("AA-AA-01"), "Expected to find 'FI-FW-01' in the results.");
    }
}