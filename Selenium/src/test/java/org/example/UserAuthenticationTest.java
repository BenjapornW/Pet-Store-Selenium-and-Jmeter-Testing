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

import static org.junit.jupiter.api.Assertions.*;
@SpiraTestConfiguration(
        url = "https://rmit.spiraservice.net",
        login = "s3961136",
        rssToken = "{C92814C3-73EF-47B2-9007-4547F84D90B4}",
        projectId = 122
)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAuthenticationTest {

    private static ChromeDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oilxgriffon/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void beforeEachTestSetup() {
        // Common steps that were repeated in most tests
        driver.get("https://petstore.octoperf.com/");
        driver.findElement(By.linkText("Enter the Store")).click();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.close();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 3674)
    public void testRegister_ValidInputs() {

        //3. Locate the Sign In hyperlink and Click
        driver.findElement(By.linkText("Sign In")).click();

        // 4. Locate the Register now hyperlink and Click
        driver.findElement(By.linkText("Register Now!")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // 15 seconds timeout
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Account.action?newAccountForm="));

        // 5. Populate user registration form
        // Account Information
        driver.findElement(By.name("username")).sendKeys("Alice_test22");
        driver.findElement(By.name("password")).sendKeys("Password123!");
        driver.findElement(By.name("repeatedPassword")).sendKeys("password123!");
        driver.findElement(By.name("account.firstName")).sendKeys("Alice");
        driver.findElement(By.name("account.lastName")).sendKeys("Bob");
        driver.findElement(By.name("account.email")).sendKeys("alicebob_test@example.com");
        driver.findElement(By.name("account.phone")).sendKeys("1234567890");
        driver.findElement(By.name("account.address1")).sendKeys("123 Main St");
        driver.findElement(By.name("account.city")).sendKeys("SampleCity");
        driver.findElement(By.name("account.state")).sendKeys("State");
        driver.findElement(By.name("account.zip")).sendKeys("12345");
        driver.findElement(By.name("account.country")).sendKeys("SampleCountry");

        // Profile information
        new Select(driver.findElement(By.name("account.languagePreference"))).selectByVisibleText("english");
        new Select(driver.findElement(By.name("account.favouriteCategoryId"))).selectByVisibleText("DOGS");
        driver.findElement(By.name("account.listOption")).click(); // For receiving promotional emails
        driver.findElement(By.name("account.bannerOption")).click(); // For enabling MyList side menu and banner

        // 6. Submit the form
        driver.findElement(By.name("newAccount")).click();

        // After clicking the newAccount button
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Catalog.action"));

        // 7. Verify registration success by checking the URL
        assertEquals("https://petstore.octoperf.com/actions/Catalog.action", driver.getCurrentUrl(), "Registration was not successful.");
    }

    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 3675)
    public void testRegister_InvalidInputs() {

        // Locate the Sign In hyperlink and Click
        driver.findElement(By.linkText("Sign In")).click();

        // Locate the Register now hyperlink and Click
        driver.findElement(By.linkText("Register Now!")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // 15 seconds timeout
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Account.action?newAccountForm="));

        // Populate user registration form with invalid data
        // Account Information
        driver.findElement(By.name("username")).sendKeys(""); // blank username
        driver.findElement(By.name("password")).sendKeys("short"); // Short password
        driver.findElement(By.name("repeatedPassword")).sendKeys("mismatch"); // Mismatching password
        driver.findElement(By.name("account.firstName")).sendKeys("1234"); // Numeric first name
        driver.findElement(By.name("account.lastName")).sendKeys(""); // Empty last name
        driver.findElement(By.name("account.email")).sendKeys("invalidEmail"); // Invalid email format
        driver.findElement(By.name("account.phone")).sendKeys("abcde"); // Alphabets in phone number
        driver.findElement(By.name("account.address1")).sendKeys(""); // Empty address
        driver.findElement(By.name("account.city")).sendKeys(""); // Empty city
        driver.findElement(By.name("account.state")).sendKeys("1234"); // Numeric state
        driver.findElement(By.name("account.zip")).sendKeys("abc"); // Alphabets in zip
        driver.findElement(By.name("account.country")).sendKeys(""); // Empty country

        // Profile information (these can be valid as they don't cause registration failure by themselves)
        new Select(driver.findElement(By.name("account.languagePreference"))).selectByVisibleText("english");
        new Select(driver.findElement(By.name("account.favouriteCategoryId"))).selectByVisibleText("DOGS");
        driver.findElement(By.name("account.listOption")).click();
        driver.findElement(By.name("account.bannerOption")).click();

        // Submit the form
        driver.findElement(By.name("newAccount")).click();

        // Verify registration failure by checking the URL (assuming the URL doesn't change upon failed registration)
        assertNotEquals("https://petstore.octoperf.com/actions/Catalog.action", driver.getCurrentUrl(), "Registration was successful with invalid data.");
    }

    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 3678)
    public void testSignIn_InvalidInputs() {

        // Locate the Sign In hyperlink and Click

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        // Clear and then enter an invalid username
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("InvalidUsername");

        // Clear and then enter an invalid password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("InvalidPassword");

        // Click the login button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signon")));
        driver.findElement(By.name("signon")).click();

        // Use explicit wait to wait for the error message to appear
        wait.until(ExpectedConditions.urlToBe("https://petstore.octoperf.com/actions/Account.action"));

        // Verify sign in success
        assertFalse(driver.getPageSource().contains("Invalid username or password. Signon failed."));
    }

    @Test
    @Order(4)
    @SpiraTestCase(testCaseId = 3677)
    public void testSignIn_ValidInputs() {

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

        // Verify sign in success
        assertTrue(driver.getPageSource().contains("Welcome Alice!"), "Expected welcome message not found.");
    }

    @Test
    @Order(5)
    @SpiraTestCase(testCaseId = 4104)
    public void testSignOut() {

        // Locate the Sign In hyperlink and Click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'MenuContent\']/a[2]")));
        driver.findElement(By.xpath("//*[@id=\'MenuContent\']/a[2]")).click();

        // Clear and then enter username
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("Alice_test");

        // Clear and then enter password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("Password123!");

        // Click the SignIn button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signon")));
        driver.findElement(By.name("signon")).click();

        // Click the SignOut button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));
        driver.findElement(By.linkText("Sign Out")).click();

        // Assert that the Sign In link is present after signing out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuContent\"]/a[2]")));
        boolean isSignInLinkPresent = driver.findElement(By.linkText("Sign In")).isDisplayed();
        assertTrue(isSignInLinkPresent);
    }
}