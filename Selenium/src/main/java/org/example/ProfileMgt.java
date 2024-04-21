package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileMgt {
    public void ProfileMgtTest() {}

    public static void main(String[] args) throws InterruptedException {

        // Setup Chrome Webdriver path
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        // Create instance of our driver
        WebDriver driver = new ChromeDriver();

        // Set up an implicit wait in case of slow connection / page load / etc.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /* Profile management */
        // 1. Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");
        driver.manage().window().maximize();

        // 3. Locate the Sign In hyperlink and Click
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

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[3]")));

        // Click My Account
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[3]")).click();

        driver.findElement(By.name("account.firstName")).clear();
        driver.findElement(By.name("account.firstName")).sendKeys("UpdatedAlice");
        driver.findElement(By.name("account.lastName")).clear();
        driver.findElement(By.name("account.lastName")).sendKeys("UpdatedBob");
        driver.findElement(By.name("account.email")).clear();
        driver.findElement(By.name("account.email")).sendKeys("updatedalicebob@example.com");
        driver.findElement(By.name("account.phone")).clear();
        driver.findElement(By.name("account.phone")).sendKeys("0987654321");
        driver.findElement(By.name("account.address1")).clear();
        driver.findElement(By.name("account.address1")).sendKeys("456 Updated St");
        driver.findElement(By.name("account.city")).clear();
        driver.findElement(By.name("account.city")).sendKeys("UpdatedCity");
        driver.findElement(By.name("account.state")).clear();
        driver.findElement(By.name("account.state")).sendKeys("UpdatedState");
        driver.findElement(By.name("account.zip")).clear();
        driver.findElement(By.name("account.zip")).sendKeys("54321");
        driver.findElement(By.name("account.country")).clear();
        driver.findElement(By.name("account.country")).sendKeys("UpdatedCountry");

        // Update Profile Information
        new Select(driver.findElement(By.name("account.languagePreference"))).selectByVisibleText("english");
        new Select(driver.findElement(By.name("account.favouriteCategoryId"))).selectByVisibleText("BIRDS");

        // Assuming you want to set listOption and bannerOption to true, only click if they are not selected.
        WebElement listOption = driver.findElement(By.name("account.listOption"));
        if (!listOption.isSelected()) {
            listOption.click();
        }

        WebElement bannerOption = driver.findElement(By.name("account.bannerOption"));
        if (!bannerOption.isSelected()) {
            bannerOption.click();
        }

        // Click Save Account Information
        driver.findElement(By.name("editAccount")).click();

        Thread.sleep(3000);
        // Click My Account to check if the updated info is shown
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[3]")).click();


        Thread.sleep(5000);
        // Update password;
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("NewPassword123!");

        Thread.sleep(2000);
        driver.findElement(By.name("repeatedPassword")).clear();
        driver.findElement(By.name("repeatedPassword")).sendKeys("NewPassword123!");

        Thread.sleep(3000);
        // Click Save Account Information
        driver.findElement(By.name("editAccount")).click();


        // Click Sign Out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        // Locate Sign in button and click
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        Thread.sleep(3000);
        // Re-find the search field and clear
        usernameField = driver.findElement(By.name("username"));
        passwordField = driver.findElement(By.name("password"));
        wait.until(ExpectedConditions.visibilityOf(usernameField));

        // ** Re-log in to check if the new password works as expected **
        // Clear and then enter username
        usernameField.clear();
        usernameField.sendKeys("UserAlice_4");


        // Clear and then enter password
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        Thread.sleep(2000);
        passwordField.sendKeys("NewPassword123!");

        // Click the login button
        driver.findElement(By.name("signon")).click();

    }
}
