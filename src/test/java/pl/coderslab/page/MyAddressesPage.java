package pl.coderslab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MyAddressesPage {
    WebDriver driver;

    @FindBy(name = "alias")
    WebElement aliasField;
    @FindBy(name = "address1")
    WebElement addressField;
    @FindBy(name = "postcode")
    WebElement postcodeField;
    @FindBy(name = "city")
    WebElement cityField;
    @FindBy(name = "phone")
    WebElement phoneField;
    @FindBy(name = "id_country")
    WebElement countryDropdown;
    @FindBy(css = ".btn.btn-primary.float-xs-right")
    WebElement saveBttn;

    public MyAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillAddressForm(String alias, String address, String postcode, String city, String phone) {
        aliasField.clear();
        aliasField.sendKeys(alias);
        addressField.clear();
        addressField.sendKeys(address);
        postcodeField.clear();
        postcodeField.sendKeys(postcode);
        cityField.clear();
        cityField.sendKeys(city);
        phoneField.sendKeys(phone);
    }

    public void selectCountry() {
        Select dropdown = new Select(countryDropdown);
        dropdown.selectByVisibleText("United Kingdom");
    }

    public void clickSaveBttn() {
        saveBttn.click();
    }


}
