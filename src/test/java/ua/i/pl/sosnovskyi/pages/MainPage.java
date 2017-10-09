package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Sosnovskyi on 09.10.2017.
 */
public class MainPage {
    private EventFiringWebDriver driver;
    private By productLinkSelector = By.cssSelector(".product-title>a");
    private List<String> productNames = new ArrayList<>();
    private List<WebElement> links;
    private WebElement newElement;

    public MainPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductsNames() {

        links = driver.findElements(productLinkSelector);
        for (WebElement element : links) {
            productNames.add(element.getText().toLowerCase());
        }
        return new ArrayList<>(productNames);
    }

    public boolean isGoodPresents(String name) {
        links = driver.findElements(productLinkSelector);
        for (WebElement element : links) {
            if (element.getText().toLowerCase().equals(name.toLowerCase())) {
                newElement=element;
                return true;
            }
        }

        return false;
    }
    public void newGoodClick(){
        newElement.click();
    }
}
