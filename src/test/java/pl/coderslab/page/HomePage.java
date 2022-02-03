package pl.coderslab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(css = ".user-info > a:nth-child(1)")
    WebElement signInButton;
    @FindBy(xpath = "//a[contains(.,'Hummingbird printed sweater')]")
    WebElement itemHumingbirdSweater;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signInClick() {
        signInButton.click();
    }

    public void chooseSweater() {itemHumingbirdSweater.click();}
}
