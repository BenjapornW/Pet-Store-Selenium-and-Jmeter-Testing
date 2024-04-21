package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAuthentication {

    public void testUserAuthentication(){}
    public static void main(String[] args) throws InterruptedException {
        // Setup Chrome Webdriver path
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        // Create instance of our driver
        WebDriver driver = new ChromeDriver();

        // Set up an implicit wait in case of slow connection / page load / etc.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));



        /* User registration and Sign in */
        // 1. Browse the Home page.
        driver.get("https://petstore.octoperf.com");

        //2. Locate the Enter the Store hyperlink and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3. Locate the Sign In hyperlink and Click
        driver.findElement(By.linkText("Sign In")).click();

        //4. Locate the Register now hyperlink and Click
        driver.findElement(By.linkText("Register Now!")).click();

        // 5. Populate user registration form
        // Account Information
        driver.findElement(By.name("username")).sendKeys("UserAlice_9");
        driver.findElement(By.name("password")).sendKeys("Password123!");
        driver.findElement(By.name("repeatedPassword")).sendKeys("password123!");
        driver.findElement(By.name("account.firstName")).sendKeys("Alice");
        driver.findElement(By.name("account.lastName")).sendKeys("Bob");
        driver.findElement(By.name("account.email")).sendKeys("alicebobtest@example.com");
        driver.findElement(By.name("account.phone")).sendKeys("1234567890");
        driver.findElement(By.name("account.address1")).sendKeys("123 Main St");
        driver.findElement(By.name("account.city")).sendKeys("SampleCity");
        driver.findElement(By.name("account.state")).sendKeys("State");
        driver.findElement(By.name("account.zip")).sendKeys("12345");
        driver.findElement(By.name("account.country")).sendKeys("SampleCountry");

        // Profile information
        new Select(driver.findElement(By.name("account.languagePreference"))).selectByVisibleText("english");
        new Select(driver.findElement(By.name("account.favouriteCategoryId"))).selectByVisibleText("DOGS");
        driver.findElement(By.name("account.listOption")).click();
        driver.findElement(By.name("account.bannerOption")).click();

        // 6. Submit the form
        driver.findElement(By.xpath("//*[@id='Catalog']/form/input")).click();

        // After clicking the newAccount button
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Catalog.action"));

        // 7. Sign In
        driver.findElement(By.linkText("Sign In")).click();

        // Clear and then enter username
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("UserAlice_4");

        // Clear and then enter password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("Password123!");

        // Click the login button
        driver.findElement(By.name("signon")).click();

        Thread.sleep(3000);
        By signOutTextLocator = By.xpath("//*[contains(text(), 'Sign Out')]");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(signOutTextLocator, "Sign Out"));

        //click the signout button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/a[2]")).click();

    }
}