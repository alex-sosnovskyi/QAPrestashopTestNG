package ua.i.pl.sosnovskyi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by A Sosnovskyi on 10.10.2017.
 */
public class CurrentProductPage {
    private EventFiringWebDriver driver;
    private By priceSelector = By.cssSelector(".current-price>span");
    private By quantitySelector = By.cssSelector(".product-quantities>span");

    public CurrentProductPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }
    public float getPrice(){
        WebElement element=driver.findElement(priceSelector);
       float result= Float.valueOf(element.getAttribute("content"));
        return result;
    }
    public int getQuantity(){
        WebElement element=driver.findElement(quantitySelector);
        int result=0;
        String resultStr=element.getText();
        String regExp="[\\d]{1,}";
        Pattern pattern=Pattern.compile(regExp);
        Matcher matcher=pattern.matcher(resultStr);
        while (matcher.find()){
            result=Integer.parseInt(matcher.group())   ;
        }
        return result;
    }
}
