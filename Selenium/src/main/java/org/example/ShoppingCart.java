package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCart {
    public void ShoppingCartTest() {}

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

        //3. Locate the Sign In hyperlink and Click
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

        // 2. Locate the Dogs text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[2]/img")).click();

        // 3. Locate the Male Poodle text and Click
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();

        // 4. Locate the Add to Cart button and Click
        driver.findElement(By.className("Button")).click();

        // 5. **Add more products** - Locate the Cats text and Click
        driver.findElement(By.xpath("//*[@id=\'QuickLinks\']/a[4]/img")).click();

        // 6. Locate the Male Persian text and Click
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a")).click();

        // 7. Locate the Add to Cart button and Click
        driver.findElement(By.className("Button")).click();


        // 8. ** Update the quantity **
        // Locate the quantity input field
        WebElement quantityField1 = driver.findElement(By.name("EST-8"));
        WebElement quantityField2 = driver.findElement(By.name("EST-16"));

        // Clear the current value and set the new value
        quantityField1.clear();
        quantityField1.sendKeys("2");
        quantityField2.clear();
        quantityField2.sendKeys("3");

        Thread.sleep(2000);
        // 9. Click Update Cart button
        driver.findElement(By.name("updateCartQuantities")).click();

        Thread.sleep(2000);
        // 10. ** Remove Poodle Dogs **
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[2]/td[8]/a")).click();

        // 9. Click Update Cart button
        driver.findElement(By.name("updateCartQuantities")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/a")));
        // 11. ** Click Proceed to Checkout **
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/a")).click();
        //Click Continue
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Order.action?newOrderForm="));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Catalog\"]/form/input")));
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();
        // Click Confirm
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Order.action"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'Catalog\']/a")));
        driver.findElement(By.xpath("//*[@id=\'Catalog\']/a")).click();
        // Click My Account
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[3]")));
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[3]")).click();
        // Click My Orders
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'Catalog\']/a")));
        driver.findElement(By.xpath("//*[@id=\'Catalog\']/a")).click();

    }
}
