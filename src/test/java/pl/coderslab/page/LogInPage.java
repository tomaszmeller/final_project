package pl.coderslab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    WebDriver driver;

    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement psswdField;
    @FindBy(css = ".btn.btn-primary")
    WebElement logInBttn;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillEmailAndPsswd(String email,String psswd) {
        emailField.clear();
        emailField.sendKeys(email);
        psswdField.clear();
        psswdField.sendKeys(psswd);
    }

    public void clickLogIn() {
        logInBttn.click();
    }
}
