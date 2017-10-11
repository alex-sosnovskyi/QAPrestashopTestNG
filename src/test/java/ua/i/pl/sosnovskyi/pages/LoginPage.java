package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by A Sosnovskyi on 07.10.2017.
 */
public class LoginPage {
    private EventFiringWebDriver driver;
    private WebElement loginForm;
    private By email = By.id("email");
    private By passwrd = By.id("passwd");
    private By submit = By.name("submitLogin");

    public LoginPage(EventFiringWebDriver driver) {
        if (driver == null) {
            throw new RuntimeException("Driver is not available!!!");
        }
        this.driver = driver;
    }

    public void openPage(String url) {
        if (url == null) {
            throw new RuntimeException("Page is null!!!");
        }
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            throw new RuntimeException("url is incorrect!!!");
        }

    }

    public void fillEmailInput(String login) {
        try {
            loginForm = driver.findElement(By.id("login_form"));
        } catch (Exception e) {
            throw new RuntimeException("login_form not found");
        }
        if (loginForm == null) {
            throw new RuntimeException("login form is not available!!!");
        }
        WebElement element = loginForm.findElement(email);
        element.sendKeys(login);
    }

    public void fillPassInput(String password) {
        if (loginForm == null) {
            throw new RuntimeException("login form is not available!!!");
        }
        driver.findElement(passwrd).sendKeys(password);
    }

    public void clickLoginButton() {
        if (loginForm == null) {
            throw new RuntimeException("login form is not available!!!");
        }
        driver.findElement(submit).click();
    }
}
