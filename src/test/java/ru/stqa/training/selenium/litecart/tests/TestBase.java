package ru.stqa.training.selenium.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.litecart.app.Application;
import ru.stqa.training.selenium.litecart.pages.BucketPage;
import ru.stqa.training.selenium.litecart.pages.MainPage;
import ru.stqa.training.selenium.litecart.pages.ProductPage;

import java.util.Set;

public class TestBase {

    public Application app;

    @Before
    public void start() {
        app = new Application();
    }

    @After
    public void stop() {
        app.driver.quit();
    }

    public void login(String username, String password) {
        app.driver.findElement(By.name("username")).sendKeys(username);
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.name("login")).click();
    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public ExpectedCondition<String> thereIsWindowOtherThan (Set<String> oldWindows) {
        Set<String> newWindows = app.driver.getWindowHandles();
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
