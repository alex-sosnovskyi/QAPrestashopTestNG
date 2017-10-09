package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by A Sosnovskyi on 09.10.2017.
 */
public class IndexPage {
    private EventFiringWebDriver driver;
    private By allGoodsSelector = By.className("all-product-link");

    public IndexPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void allGoodsClick(){
        driver.findElement(allGoodsSelector).click();
    }
}
