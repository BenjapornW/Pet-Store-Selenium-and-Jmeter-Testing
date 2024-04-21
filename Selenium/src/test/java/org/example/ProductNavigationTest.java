package org.example;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;
@SpiraTestConfiguration(
        url = "https://rmit.spiraservice.net",
        login = "s3961136",
        rssToken = "{C92814C3-73EF-47B2-9007-4547F84D90B4}",
        projectId = 122
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductNavigationTest {

    private static ChromeDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 3984)
    public void testProductNavigate_Fish() {
        // Navigate to the fish page
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[1]/img")).click();

        // Assertions to ensure the expected data appears on the fish page
        assertTrue(driver.getPageSource().contains("FI-SW-01"), "Product ID FI-SW-01 not found.");
        assertTrue(driver.getPageSource().contains("Angelfish"), "Product Name Angelfish not found.");

        assertTrue(driver.getPageSource().contains("FI-SW-02"), "Product ID FI-SW-02 not found.");
        assertTrue(driver.getPageSource().contains("Tiger Shark"), "Product Name Tiger Shark not found.");

        assertTrue(driver.getPageSource().contains("FI-FW-01"), "Product ID FI-FW-01 not found.");
        assertTrue(driver.getPageSource().contains("Koi"), "Product Name Koi not found.");

        assertTrue(driver.getPageSource().contains("FI-FW-02"), "Product ID FI-FW-02 not found.");
        assertTrue(driver.getPageSource().contains("Goldfish"), "Product Name Goldfish not found.");
    }


    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 3985)
    public void testProductNavigate_Dogs() {
        // Navigate to the dogs page
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();

        // Assertions to ensure the expected data appears on the dogs page
        assertTrue(driver.getPageSource().contains("K9-BD-01"), "Product ID K9-BD-01 not found.");
        assertTrue(driver.getPageSource().contains("Bulldog"), "Product Name Bulldog not found.");

        assertTrue(driver.getPageSource().contains("K9-PO-02"), "Product ID K9-PO-02 not found.");
        assertTrue(driver.getPageSource().contains("Poodle"), "Product Name Poodle not found.");

        assertTrue(driver.getPageSource().contains("K9-DL-01"), "Product ID K9-DL-01 not found.");
        assertTrue(driver.getPageSource().contains("Dalmation"), "Product Name Dalmation not found.");

        assertTrue(driver.getPageSource().contains("K9-RT-01"), "Product ID K9-RT-01 not found.");
        assertTrue(driver.getPageSource().contains("Golden Retriever"), "Product Name Golden Retriever not found.");

        assertTrue(driver.getPageSource().contains("K9-RT-02"), "Product ID K9-RT-02 not found.");
        assertTrue(driver.getPageSource().contains("Labrador Retriever"), "Product Name Labrador Retriever not found.");

        assertTrue(driver.getPageSource().contains("K9-CW-01"), "Product ID K9-CW-01 not found.");
        assertTrue(driver.getPageSource().contains("Chihuahua"), "Product Name Chihuahua not found.");
    }

    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 3986)
    public void testProductNavigate_Reptiles() {
        // Navigate to the reptiles page
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[3]/img")).click();

        // Assertions to ensure the expected data appears on the reptiles page
        assertTrue(driver.getPageSource().contains("RP-SN-01"), "Product ID RP-SN-01 not found.");
        assertTrue(driver.getPageSource().contains("Rattlesnake"), "Product Name Rattlesnake not found.");

        assertTrue(driver.getPageSource().contains("RP-LI-02"), "Product ID RP-LI-02 not found.");
        assertTrue(driver.getPageSource().contains("Iguana"), "Product Name Iguana not found.");
    }

    @Test
    @Order(4)
    @SpiraTestCase(testCaseId = 3987)
    public void testProductNavigate_Cats() {
        // Navigate to the cats page
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[4]/img")).click();

        // Assertions to ensure the expected data appears on the cats page
        assertTrue(driver.getPageSource().contains("FL-DSH-01"), "Product ID FL-DSH-01 not found.");
        assertTrue(driver.getPageSource().contains("Manx"), "Product Name Manx not found.");

        assertTrue(driver.getPageSource().contains("FL-DLH-02"), "Product ID FL-DLH-02 not found.");
        assertTrue(driver.getPageSource().contains("Persian"), "Product Name Persian not found.");
    }

    @Test
    @Order(5)
    @SpiraTestCase(testCaseId = 3988)
    public void testProductNavigate_Birds() {
        // Navigate to the birds page
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[5]/img")).click();

        // Assertions to ensure the expected data appears on the birds page
        assertTrue(driver.getPageSource().contains("AV-CB-01"), "Product ID AV-CB-01 not found.");
        assertTrue(driver.getPageSource().contains("Amazon Parrot"), "Product Name Amazon Parrot not found.");

        assertTrue(driver.getPageSource().contains("AV-SB-02"), "Product ID AV-SB-02 not found.");
        assertTrue(driver.getPageSource().contains("Finch"), "Product Name Finch not found.");
    }

}