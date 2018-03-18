package ru.stqa.training.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class MenuTest extends TestBase {

    @Test
    public void testClickAllMenuItems() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        clickMenuItems();
    }

    private void clickMenuItems() {
        List<WebElement> verticalMenu = driver.findElements(By.id("app-"));
        for (int i = 0; i < verticalMenu.size(); i++) {
            WebElement menuItem = verticalMenu.get(i);
            menuItem.click();
            List<WebElement> innerMenu = driver.findElements(By.cssSelector("ul.docs li"));
            if (innerMenu.size() > 0) {
                for (int n = 0; n < innerMenu.size(); n++) {
                    WebElement innerMenuItem = innerMenu.get(n);
                    innerMenuItem.click();
                    assertTrue(areElementsPresent(driver, By.tagName("h1")));
                    innerMenu = driver.findElements(By.cssSelector("ul.docs li"));
                }
            }
            else assertTrue(areElementsPresent(driver, By.tagName("h1")));
            verticalMenu = driver.findElements(By.id("app-"));
        }
    }


}
