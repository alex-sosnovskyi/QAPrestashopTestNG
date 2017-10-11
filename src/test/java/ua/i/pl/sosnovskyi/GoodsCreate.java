package ua.i.pl.sosnovskyi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ua.i.pl.sosnovskyi.pages.*;
import ua.i.pl.sosnovskyi.utils.EventHandler;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by A Sosnovskyi on 06.10.2017.
 */
public class GoodsCreate {
    WebDriver baseDriver;
    EventFiringWebDriver driver;
    private static final String DEFAULT_BASE_ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private static final String DEFAULT_BASE_URL = "http://prestashop-automation.qatestlab.com.ua/";
    String nameToAssert;
    int quantityToAssert;
    float priceToAssert;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browser) {
        switch (browser) {
            case "chrome": {
                String key = System.getProperty("webdriver.chrome.driver");
                String path = new File(GoodsCreate.class.getResource("/chromedriver.exe").getFile()).getPath();
                if (key == null) {
                    System.setProperty("webdriver.chrome.driver", path);
                }
                baseDriver = new ChromeDriver();
                break;

            }
            case "gecko": {
                String key = System.getProperty("webdriver.gecko.driver");
                String path = new File(GoodsCreate.class.getResource("/geckodriver.exe").getFile()).getPath();
                if (key == null) {
                    System.setProperty("webdriver.gecko.driver", path);
                }
                baseDriver = new FirefoxDriver();
                break;
            }
            case "ie": {
                String key = System.getProperty("webdriver.ie.driver");
                String path = new File(GoodsCreate.class.getResource("/IEDriverServer.exe").getFile()).getPath();
                if (key == null) {
                    System.setProperty("webdriver.ie.driver", path);

                }
                baseDriver = new InternetExplorerDriver();
                break;
            }
            default: {
                throw new RuntimeException("Wrong browser name");
            }
        }

    }

    @BeforeClass
    public void configureDriver() {

        baseDriver.manage().window().maximize();
        baseDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EventFiringWebDriver driver = new EventFiringWebDriver(baseDriver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.register(new EventHandler());
        this.driver = driver;
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {
        return new String[][]{{"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}};
    }

    @Test(dataProvider = "getData")
    public void loginTest(String email, String password) {
//        driver.navigate().to(DEFAULT_BASE_ADMIN_URL);
//        driver.findElement(By.id("email")).sendKeys(email);
//        driver.findElement(By.id("passwd")).sendKeys(password);
//        driver.findElement(By.name("submitLogin")).click();
        LoginPage page = new LoginPage(driver);
        page.openPage(DEFAULT_BASE_ADMIN_URL);
        page.fillEmailInput(email);
        page.fillPassInput(password);
        page.clickLoginButton();
        Assert.assertEquals(driver.getTitle(),
                "prestashop-automation > Панель администратора (PrestaShop™)",
                "Page is not named prestashop-automation > Панель администратора (PrestaShop™) it named "
                        + driver.getTitle());
    }

    @Test(dependsOnMethods = "loginTest")
    public void addGood() throws InterruptedException {
        DashbordPage dashbordPage = new DashbordPage(driver);
        dashbordPage.submenuGoodsItemClick();
        Assert.assertEquals(driver.getTitle(), "товары • prestashop-automation",
                "Page is not named товары • prestashop-automation it named " + driver.getTitle());
        GoodsPage goodsPage = new GoodsPage(driver);
        goodsPage.newGoodClick();
        nameToAssert = goodsPage.newGoodNameFill();
        quantityToAssert = goodsPage.newGoodQuantityFill();
        priceToAssert = goodsPage.newGoodPriceFill();
        goodsPage.activateClick();
        goodsPage.saveButtonClick();
    }

    @Test(dependsOnMethods = "addGood")
    public void goodDisplayTest() throws InterruptedException {
        driver.navigate().to(DEFAULT_BASE_URL);
        Assert.assertEquals(driver.getTitle(), "prestashop-automation", "Page tittle is not prestashop-automation");
        IndexPage indexPage = new IndexPage(driver);
        indexPage.allGoodsClick();
        MainPage mainPage = new MainPage(driver);
//        List<String> namesOfProducts = mainPage.getProductsNames();
//        System.out.println("nameToAssert " + nameToAssert);
//        for (String str : namesOfProducts) {
//            System.out.println(str);
//        }

        Assert.assertEquals(mainPage.isGoodPresents(nameToAssert), true, "product is missing");
        mainPage.newGoodClick();
        Assert.assertEquals(driver.getTitle(), nameToAssert, "It's wrong page");
        CurrentProductPage currentProductPage = new CurrentProductPage(driver);
        Assert.assertEquals(currentProductPage.getPrice(), priceToAssert, 0.01, "Price is invalid");
        Assert.assertEquals(currentProductPage.getQuantity(), quantityToAssert, "Quantity is invalid");

    }
}
