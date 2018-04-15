package ru.stqa.training.selenium.litecart.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.litecart.pages.BucketPage;
import ru.stqa.training.selenium.litecart.pages.MainPage;
import ru.stqa.training.selenium.litecart.pages.ProductPage;


public class Application {

    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public ProductPage productPage;
    public BucketPage bucketPage;

    public Application() {
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

}
