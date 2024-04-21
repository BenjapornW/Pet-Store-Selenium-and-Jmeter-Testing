package org.example;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpiraTestConfiguration(
        url = "https://rmit.spiraservice.net",
        login = "s3961136",
        rssToken = "{C92814C3-73EF-47B2-9007-4547F84D90B4}",
        projectId = 122
)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileMgtTest {

    private static ChromeDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. Browse the Home page.
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        // 3. Locate the Sign In hyperlink and Click
        driver.findElement(By.linkText("Sign In")).click();

        // Clear and then enter username
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("UserAlice_5");

        // Clear and then enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("Password123!");

        // Click the login button
        driver.findElement(By.name("signon")).click();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 4102)
    public void testAccountUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("editAccount")));
        driver.findElement(By.name("editAccount")).click();

        // Click My Account to check if the updated info is shown
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[3]")).click();

        // Once the account update is completed, verify that the updated data is visible on the page.
        assertEquals("UpdatedAlice", driver.findElement(By.name("account.firstName")).getAttribute("value"), "First name was not updated correctly.");
        assertEquals("UpdatedBob", driver.findElement(By.name("account.lastName")).getAttribute("value"), "Last name was not updated correctly.");
        assertEquals("updatedalicebob@example.com", driver.findElement(By.name("account.email")).getAttribute("value"), "Email was not updated correctly.");
        assertEquals("0987654321", driver.findElement(By.name("account.phone")).getAttribute("value"), "Phone number was not updated correctly.");
        assertEquals("456 Updated St", driver.findElement(By.name("account.address1")).getAttribute("value"), "Address was not updated correctly.");
        assertEquals("UpdatedCity", driver.findElement(By.name("account.city")).getAttribute("value"), "City was not updated correctly.");
        assertEquals("UpdatedState", driver.findElement(By.name("account.state")).getAttribute("value"), "State was not updated correctly.");
        assertEquals("54321", driver.findElement(By.name("account.zip")).getAttribute("value"), "ZIP code was not updated correctly.");
        assertEquals("UpdatedCountry", driver.findElement(By.name("account.country")).getAttribute("value"), "Country was not updated correctly.");

        // Further assertions can be added as per other UI elements and expected outcomes.
        // For the Select elements
        Select languagePreference = new Select(driver.findElement(By.name("account.languagePreference")));
        assertEquals("english", languagePreference.getFirstSelectedOption().getText(), "Language preference was not updated correctly.");

        Select favouriteCategory = new Select(driver.findElement(By.name("account.favouriteCategoryId")));
        assertEquals("BIRDS", favouriteCategory.getFirstSelectedOption().getText(), "Favorite category was not updated correctly.");

        // For the checkboxes
        assertTrue(driver.findElement(By.name("account.listOption")).isSelected(), "List option was not updated correctly.");
        assertTrue(driver.findElement(By.name("account.bannerOption")).isSelected(), "Banner option was not updated correctly.");
    }

    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 4552)
    public void testUpdatePassword() throws InterruptedException {

        // Click My Account
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
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("editAccount")));
        driver.findElement(By.name("editAccount")).click();


        // Click Sign Out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        // Locate Sign in button and click
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        Thread.sleep(3000);
        // Re-find the search field and clear
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        wait.until(ExpectedConditions.visibilityOf(usernameField));

        // ** Re-log in to check if the new password works as expected **
        // Clear and then enter username
        usernameField.clear();
        usernameField.sendKeys("UserAlice_5");

        // Clear and then enter password
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        Thread.sleep(2000);
        passwordField.sendKeys("NewPassword123!");

        // Click the Signin button

        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Account.action?signonForm="));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signon")));
        driver.findElement(By.name("signon")).click();

        // If SignOut link is present, it indicates successful login, which is not expected
        assertTrue(driver.getPageSource().contains("Sign Out"));
    }


}