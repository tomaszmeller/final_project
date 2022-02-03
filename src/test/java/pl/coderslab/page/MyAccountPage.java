package pl.coderslab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver driver;

    @FindBy(id = "address-link")
    WebElement firstAddressBttn;
    @FindBy(xpath = "//div[@class='address-body']")
    WebElement addressBox;
    @FindBy(xpath = "//a[@href='https://mystore-testlab.coderslab.pl/index.php?mylogout=']")
    WebElement signOutBttn;
    @FindBy(xpath = "//a[@data-link-action='delete-address']")
    WebElement deleteBttn;
//    @FindBy(xpath = "//a[@href='https://mystore-testlab.coderslab.pl/'")
    @FindBy(xpath = "//*[@id='_desktop_logo']/h1/a")
    WebElement mainPageBttn;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFilledAddress() {
        return addressBox.getText();
    }

    public void deleteAddress() {
        deleteBttn.click();
    }

    public void clickAddAddressBttn() {
        firstAddressBttn.click();
    }

    public void clickSignOutBttn() {
        signOutBttn.click();
    }

    public void clickMainPageBttn() {
        mainPageBttn.click();
    }
}
