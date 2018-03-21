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
        goToMenuItemPage("Countries");
        checkCountriesOrder();
    }

    @Test
    public void testGeoZonesOrder() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        goToMenuItemPage("Geo Zones");
        checkGeoZonesOrder();
    }

    private void checkGeoZonesOrder() {
        List<WebElement> zoneTableRows = getZoneTableRows();
        for (int i = 0; i < zoneTableRows.size(); i++) {
            zoneTableRows.get(i).findElement(By.xpath(".//td[3]/a")).click();
            List<WebElement> zoneRows = driver.findElements(By.xpath("//table[@id='table-zones']//tr[./td/select]"));
            String previousZoneName = "";
            for (int n = 0; n < zoneRows.size(); n++) {
                String zoneName = zoneRows.get(n).findElement(By.xpath(".//select[contains(@name, '[zone_code]')]/option[@selected='selected']")).getText();
                if (n != 0) {
                    assertTrue(zoneName.compareTo(previousZoneName) > 0);
                }
                previousZoneName = zoneName;
            }
            goToMenuItemPage("Geo Zones");
            zoneTableRows = getZoneTableRows();
        }
    }

    private List<WebElement> getZoneTableRows() {
        return driver.findElements(By.cssSelector("form[name='geo_zones_form'] tr.row"));
    }

    private void checkCountriesOrder() {
        List<WebElement> rowCountries = getCountryRows();
        String previousCountryName = "";
        for (int i = 0; i < rowCountries.size(); i++) {
            WebElement cellName = getCountryNameCell(rowCountries.get(i));
            String countryName = cellName.getAttribute("textContent");
            if (i !=0) {
                assertTrue(countryName.compareTo(previousCountryName) > 0);
            }
            previousCountryName = countryName;

            WebElement cellZones = getZonesAmountCell(rowCountries.get(i));
            int zonesAmount = Integer.parseInt(cellZones.getAttribute("textContent"));
            if (zonesAmount != 0) {
                cellName.findElement(By.cssSelector("a")).click();
                WebElement zoneTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zoneNames = zoneTable.findElements(By.cssSelector("input[type='hidden'][name*='[name]']"));
                compareZoneNames(zoneNames);
                goToMenuItemPage("Countries");
                rowCountries = getCountryRows();

            }
        }
    }

    private void compareZoneNames(List<WebElement> zoneNames) {
        String previousZoneName = "";
        for (int n = 0; n < zoneNames.size(); n++) {
            String zoneName = zoneNames.get(n).getAttribute("value");
            if (n != 0) {
                assertTrue(zoneName.compareTo(previousZoneName) > 0);
            }
            previousZoneName = zoneName;
        }
    }


    private List<WebElement> getCountryRows() {
        return driver.findElements(By.cssSelector("form[name='countries_form'] tr.row"));
    }

    private void goToMenuItemPage(String title) {
        driver.findElement(By.xpath(String.format("//span[.='%s']", title))).click();
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
