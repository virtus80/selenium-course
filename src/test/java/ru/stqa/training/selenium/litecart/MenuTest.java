package ru.stqa.training.selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MenuTest extends TestBase {

    @Test
    public void testClickAllMenuItems() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        List<WebElement> verticalMenu = driver.findElements(By.id("app-"));
        for (WebElement item : verticalMenu) {
            item.click();
        }

    }




}
