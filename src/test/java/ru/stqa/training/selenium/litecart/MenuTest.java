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

    @Test
    public void testCountriesOrder() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        goToCountryPage();
        List<WebElement> rowCountries = getCountryRows();
        checkCountriesOrder(rowCountries);
        for (int i = 0; i < rowCountries.size(); i++) {
            WebElement cellName = getCountryNameCell(rowCountries.get(i));
            WebElement cellZones = getZonesAmountCell(rowCountries.get(i));
            int zonesAmount = Integer.parseInt(cellZones.getAttribute("textContent"));
            if (zonesAmount != 0) {
                cellName.findElement(By.cssSelector("a")).click();
                WebElement zoneTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zoneNames = zoneTable.findElements(By.cssSelector("input[type='hidden'][name*='[name]']"));
                String previousZoneName = "";
                for (int n = 0; n < zoneNames.size(); n++) {
                    String zoneName = zoneNames.get(n).getAttribute("value");
                    if (n != 0) {
                        assertTrue(zoneName.compareTo(previousZoneName) > 0);
                    }
                    previousZoneName = zoneName;
                }
                goToCountryPage();
                rowCountries = getCountryRows();

            }
        }
    }

    private void checkCountriesOrder(List<WebElement> rowCountries) {
        String previousCountryName = "";
        for (int i = 0; i < rowCountries.size(); i++) {
            WebElement cellName = getCountryNameCell(rowCountries.get(i));
            String countryName = cellName.getAttribute("textContent");
            if (i !=0) {
                assertTrue(countryName.compareTo(previousCountryName) > 0);
            }
            previousCountryName = countryName;
        }
    }

    private List<WebElement> getCountryRows() {
        return driver.findElements(By.cssSelector("form[name='countries_form'] tr.row"));
    }

    private void goToCountryPage() {
        driver.findElement(By.xpath("//span[.='Countries']")).click();
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
            } else assertTrue(areElementsPresent(driver, By.tagName("h1")));
            verticalMenu = driver.findElements(By.id("app-"));
        }
    }

    private WebElement getCountryNameCell(WebElement element) {
        return element.findElements(By.cssSelector("td")).get(4);
    }

    private WebElement getZonesAmountCell(WebElement element) {
        return element.findElements(By.cssSelector("td")).get(5);
    }


}
