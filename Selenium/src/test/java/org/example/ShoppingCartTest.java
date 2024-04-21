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

import static org.junit.jupiter.api.Assertions.*;
@SpiraTestConfiguration(
        url = "https://rmit.spiraservice.net",
        login = "s3961136",
        rssToken = "{C92814C3-73EF-47B2-9007-4547F84D90B4}",
        projectId = 122
)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartTest {

    private static ChromeDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // Locate the Sign In hyperlink and Click
        driver.findElement(By.linkText("Sign In")).click();

        // Clear and then enter username
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("Alice_test");

        // Clear and then enter password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("Password123!");

        // Click the login button
        driver.findElement(By.name("signon")).click();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(5000);
//        driver.quit();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 3996)
    public void testShoppingCart_Add() {
        // Add Poodle to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();
        assertTrue(driver.getPageSource().contains("Poodle"), "Expected Poodle to be added to cart.");
        // Add Persian to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[4]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();
        assertTrue(driver.getPageSource().contains("Persian"), "Expected Persian cat to be added to cart.");
    }

    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 3997)
    public void testShoppingCart_Remove() {
        // Add Poodle to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();
        // Add Persian to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[4]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();
        // remove the items
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[2]/td[8]/a")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[2]/td[8]/a")).click();

        assertFalse(driver.getPageSource().contains("Poodle"), "Did not expect Poodle to be in the cart.");
        assertFalse(driver.getPageSource().contains("Persian"), "Did not expect Persian to be in the cart.");
    }

    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 4004)
    public void testShoppingCart_Update() {
        // Add Poodle to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();

        WebElement quantityField1 = driver.findElement(By.name("EST-8"));
        quantityField1.clear();
        quantityField1.sendKeys("2");
        //Update quantity
        driver.findElement(By.name("updateCartQuantities")).click();

        // Re-find the element after the DOM has possibly changed
        WebElement updatedQuantityField1 = driver.findElement(By.name("EST-8"));

        assertEquals("2", updatedQuantityField1.getAttribute("value"));
    }

    @Test
    @Order(4)
    @SpiraTestCase(testCaseId = 4005)
    public void testShoppingCart_Calculate() {
        // Add Poodle to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();
        // Locate the quantity input field
        WebElement quantityField1 = driver.findElement(By.name("EST-8"));
        // Clear the current value and set the new value
        quantityField1.clear();
        quantityField1.sendKeys("2");
        // Click Update Cart button
        driver.findElement(By.name("updateCartQuantities")).click();
        // Re-find the element after the DOM has possibly changed
        WebElement updatedQuantityField1 = driver.findElement(By.name("EST-8"));
        float quantity = Float.parseFloat(updatedQuantityField1.getAttribute("value"));
        // Extract the List Price
        String listPriceStr = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[2]/td[6]")).getText();
        listPriceStr = listPriceStr.replace("$", ""); // Remove the dollar sign to convert it to a float
        float listPrice = Float.parseFloat(listPriceStr);
        // Calculate the expected total cost
        float expectedTotalCost = quantity * listPrice;
        // Extract the actual Total Cost from the webpage
        String actualTotalCostStr = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[2]/td[7]")).getText();
        actualTotalCostStr = actualTotalCostStr.replace("$", "");
        float actualTotalCost = Float.parseFloat(actualTotalCostStr);

        // Assert that the actual total cost matches the expected total cost
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    @Order(5)
    @SpiraTestCase(testCaseId = 4006)
    public void testShoppingCart_Checkout() {
        // Add Poodle to the cart
        driver.findElement(By.xpath("//*[@id='QuickLinks']/a[2]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();
        driver.findElement(By.className("Button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/a")));
        // Click Proceed to Checkout **
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/a")).click();
        // Click Continue
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Order.action?newOrderForm="));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Catalog\"]/form/input")));
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();
        // Click Confirm
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Order.action"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'Catalog\']/a")));
        driver.findElement(By.xpath("//*[@id=\'Catalog\']/a")).click();

        boolean isOrderSubmitted = driver.getPageSource().contains("Thank you, your order has been submitted.");
        assertTrue(isOrderSubmitted);
    }
}