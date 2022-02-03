package pl.coderslab;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.page.HomePage;
import pl.coderslab.page.LogInPage;
import pl.coderslab.page.MyAccountPage;
import pl.coderslab.page.MyAddressesPage;

import java.util.concurrent.TimeUnit;

public class AddFirstAddressSteps {
    private HomePage homePage;
    private LogInPage logInPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressPage;
    private WebDriver driver;

    @Given("Active home page and existing account credentials")
    public void goToHomePage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");
        homePage = new HomePage(driver);
    }

    @When("I click SignIn button")
    public void goToLogInPage() {
        homePage.signInClick();
        logInPage = new LogInPage(driver);
    }

    @And("I log in with email {word} and psswd {word}")
    public void logInWithCredentials(String email, String psswd) {
        logInPage.fillEmailAndPsswd(email, psswd);
        logInPage.clickLogIn();
        myAccountPage = new MyAccountPage(driver);
    }

    @And("I click ADD FIRST ADDRESS")
    public void goToAddressForm() {
        myAccountPage.clickAddAddressBttn();
        myAddressPage = new MyAddressesPage(driver);
    }

    @And("I fill fields: alias {word} address {word} city {word} zipcode {word} phone {word} and choose country")
    public void fillAddressForm(String alias, String address, String postcode, String city, String phone) {
        myAddressPage.fillAddressForm(alias, address, postcode, city, phone);
        myAddressPage.selectCountry();
    }

    @And("I submit changes by clicking SAVE")
    public void confirmByClickingSave() {
        myAddressPage.clickSaveBttn();
    }

    @Then("Success alert appears, comparing input and output data {word} {word} {word} {word} {word}")
    public void checkSuccessAdd(String alias, String address, String postcode, String city, String phone) {
        String addressText = myAccountPage.getFilledAddress();
        System.out.println(addressText);
        Assert.assertTrue(addressText.contains(address));
        Assert.assertTrue(addressText.contains(alias));
        Assert.assertTrue(addressText.contains(postcode));
        Assert.assertTrue(addressText.contains(city));
        Assert.assertTrue(addressText.contains(phone));
    }

    @And("I delete address")
    public void deleteAddres() {
        myAccountPage.deleteAddress();
    }
    @Then("I check if it's deleted")
    public void checkIfAddressStillExist() {
        Assert.assertTrue("Usunięcie nieudane", driver.findElement(By.xpath("//li[contains(.,'Address successfully deleted!')]")).isDisplayed());
    }

    @And("I log out")
    public void logOut() {
        myAccountPage.clickSignOutBttn();
    }

    @And("Close browser")
    public void closeBrowser() {
        driver.close();
    }
}
