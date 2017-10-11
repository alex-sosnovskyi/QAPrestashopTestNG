package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;


/**
 * Created by A Sosnovskyi on 07.10.2017.
 */
public class DashbordPage {
    private EventFiringWebDriver driver;

    private By catalogMenuSelector = By.cssSelector("#subtab-AdminProducts>a");

    public DashbordPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void submenuGoodsItemClick() {
        WebElement currentSubMenuItem = driver.findElement(catalogMenuSelector);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", currentSubMenuItem);
    }
}
