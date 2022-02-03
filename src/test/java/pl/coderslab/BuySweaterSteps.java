package pl.coderslab;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pl.coderslab.page.HomePage;
import pl.coderslab.page.LogInPage;
import pl.coderslab.page.MyAccountPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuySweaterSteps {
    private HomePage homePage;
    private LogInPage logInPage;
    private MyAccountPage myAccountPage;
    private WebDriver driver;

    @Given("Active home page with log in user login {word} psswd {word}")
    public void goToHomePageAsLoggedIn(String email, String psswd) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");
        homePage = new HomePage(driver);
        homePage.signInClick();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailAndPsswd(email, psswd);
        logInPage.clickLogIn();
        driver.get("https://mystore-testlab.coderslab.pl/");
        homePage = new HomePage(driver);
    }

    @When("I choose Hummingbird printed sweater")
    public void chooseItem() {
        Assert.assertTrue("Rabat = 20%", driver.findElement(By.xpath("//span[contains(.,'-20%')]")).isDisplayed());
        homePage.chooseSweater();
    }

    @And("I choose size and quantity")
    public void chooseSizeAndQty() {
        Select dropdownSize = new Select(driver.findElement(By.name("group[1]")));
        dropdownSize.selectByVisibleText("M");
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("5");
    }

    @And("I add to cart")
    public void addToCartChoosen() {
        driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']")).click();
    }

    @And("I go to check out")
    public void goToCheckOut() throws InterruptedException {
        WebElement proceed = driver.findElement(By.xpath("//a[@href='//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(proceed).click().build().perform();
        driver.findElement(By.xpath("//a[contains(.,'Proceed to checkout')]")).click();
        actions.release();
    }

    @And("I confirm address")
    public void confirmAddress() {
        driver.findElement(By.xpath("//a[contains(.,'Proceed to checkout')]")).click();
        driver.findElement(By.name("confirm-addresses")).click();
    }

    @And("I choose Shipping Method")
    public void chooseShippingMethod() {
        driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button")).click();
    }

    @And("I choose Payment Method")
    public void choosePaymentMethod() throws InterruptedException {
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.id("payment-option-1")).click();
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.cssSelector("#payment-confirmation button")).click();
    }

    @Then("Item is bought, I take a screenshot of confirmation")
    public void checkSuccesBuy() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("C:\\Users\\t_mel\\Desktop\\screeshotofconfirmation.jpeg"));
    }

    @And("I log out my account")
    public void logOut() {
        driver.findElement(By.xpath("//a[@href='https://mystore-testlab.coderslab.pl/index.php?mylogout=']")).click();
    }

    @And("I close browser")
    public void closeBrowser() {
        driver.close();
    }
}
