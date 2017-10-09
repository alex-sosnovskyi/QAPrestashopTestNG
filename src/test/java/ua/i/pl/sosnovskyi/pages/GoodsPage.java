package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.i.pl.sosnovskyi.utils.DataGenerator;


/**
 * Created by A Sosnovskyi on 07.10.2017.
 */
public class GoodsPage {
    private EventFiringWebDriver driver;
    private By newGoodButtonSelector = By.id("page-header-desc-configuration-add");
    private By newGoodInputSelector = By.id("form_step1_name_1");
    private By newGoodQuantitySelector = By.id("form_step1_qty_0_shortcut");
    private By newGoodPriceSelector = By.id("form_step1_price_shortcut");
    private By newGoodActivateSelector = By.cssSelector("div.switch-input"); //By.id("form_step1_active");
    private By saveButtonSelector = By.cssSelector("button.js-btn-save");
    private By message = By.id("growls");

    public GoodsPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void newGoodClick() {
        driver.findElement(newGoodButtonSelector).click();
    }

    public String newGoodNameFill() {
        WebElement newGoodNameInput = driver.findElement(newGoodInputSelector);
        String tempStr = new DataGenerator().getName();
        newGoodNameInput.sendKeys(tempStr);
        return tempStr;
    }

    public int newGoodQuantityFill() {
        WebElement newGoodQuantityInput = driver.findElement(newGoodQuantitySelector);
        int quantity = new DataGenerator().getQuantity();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value = ''", newGoodQuantityInput);
        newGoodQuantityInput.sendKeys(String.valueOf(quantity));
        return quantity;
    }

    public float newGoodPriceFill() {
        WebElement newGoodPriceInput = driver.findElement(newGoodPriceSelector);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value = ''", newGoodPriceInput);
        float tmp = new DataGenerator().getPrice();
        newGoodPriceInput.sendKeys(String.valueOf(tmp));
        return tmp;
    }

    public void activateClick() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", driver.findElement(newGoodActivateSelector));
    }

    public void saveButtonClick() {
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(saveButtonSelector));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", driver.findElement(saveButtonSelector));
    }

    public void closeAlert(){
        WebElement alertContainer=driver.findElement(message);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.growl-notice")));
        alertContainer.findElement(By.className("growl-close")).click();
    }
}
