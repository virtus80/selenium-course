package ru.stqa.training.selenium.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.litecart.pages.BucketPage;
import ru.stqa.training.selenium.litecart.pages.MainPage;
import ru.stqa.training.selenium.litecart.pages.ProductPage;

import java.util.Set;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public TestBase app;
    protected MainPage mainPage;
    protected ProductPage productPage;
    protected BucketPage bucketPage;

    public TestBase() {
        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();
//        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//        driver = new FirefoxDriver(options);

// working code
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability(FirefoxDriver.MARIONETTE, true);
//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        options.setBinary(bin);
//        driver = new FirefoxDriver(options);

//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//        options.addArguments("start-maximized");
//        driver = new ChromeDriver(options);
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
//        driver = new InternetExplorerDriver(caps);
//        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        bucketPage = new BucketPage(driver);
    }

    @Before
    public void start() {
        app = new TestBase();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void login(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public ExpectedCondition<String> thereIsWindowOtherThan (Set<String> oldWindows) {
        Set<String> newWindows = driver.getWindowHandles();
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                newWindows.removeAll(oldWindows);
                return newWindows.size() > 0 ?
                        newWindows.iterator().next() : null;
            }
        };
    }
}
