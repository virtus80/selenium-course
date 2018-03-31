package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.litecart.model.Product;

import java.io.File;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void testAddProduct() {
        Product product = new Product("Bee", "150810", "10", "src/test/resources/test.png",
                "15032018", "15032018", "test", "bees for you", "Very useful product", "6.55", "9.12");
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        goToMenuItemPage("Catalog");
        int itemsBefore = countCatalogItems();
        driver.findElement(By.xpath("//a[.=' Add New Product']")).click();
        fillProductPages(product);
        driver.findElement(By.name("save")).click();
        int itemsAfter = countCatalogItems();
        assertEquals(itemsBefore + 1, itemsAfter);
    }

    @Test
    public void testOpenNewWindow() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        goToMenuItemPage("Countries");
        goToEditCountryPage();
        List<WebElement> externalLinks = driver.findElements(By.xpath("//a[./i[contains(@class, 'fa-external-link')]]"));
        String currentWindow = driver.getWindowHandle();
        final Set<String> windowsBeforeOpening = driver.getWindowHandles();
        for (int i = 0; i < externalLinks.size(); i++) {
            WebElement externalLink = externalLinks.get(i);
            externalLink.click();
            String newWindow = wait.until((ExpectedCondition<String>) driver -> {
                Set<String> newWindows = driver.getWindowHandles();
                newWindows.removeAll(windowsBeforeOpening);
                if (newWindows.size() > 0) return newWindows.iterator().next();
                else return null;
            });
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(currentWindow);
        }
    }

    private void goToEditCountryPage() {
        driver.findElement(By.cssSelector("a[title=Edit]")).click();
    }

    private int countCatalogItems() {
        return driver.findElements(By.cssSelector("tr.row")).size();
    }

    private void fillProductPages(Product product) {
        fillGeneralProductPage(product);
        fillInformationProductPage(product);
        fillPriceProductPage(product);
    }


    private void fillGeneralProductPage(Product product) {
        gotoPageInProductMenu("General");
        driver.findElement(By.xpath(String.format("//label[.=' %s']/input", product.getStatus()))).click();
        driver.findElement(By.name("name[en]")).sendKeys(product.getName());
        driver.findElement(By.name("code")).sendKeys(product.getCode());
        if (!driver.findElement(By.cssSelector(String.format("[data-name='%s']", product.getCategory()))).isSelected()) {
            driver.findElement(By.cssSelector(String.format("[data-name='%s']", product.getCategory()))).click();
        }
        if (!driver.findElement(By.xpath(String.format("//tr[./td[.='Unisex']]//input[@name='product_groups[]']", product.getProductGroup()))).isSelected()) {
            driver.findElement(By.xpath(String.format("//tr[./td[.='Unisex']]//input[@name='product_groups[]']", product.getProductGroup()))).click();
        }
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys(product.getQuantity());
        Select selectQuantityUnit = new Select(driver.findElement(By.name("quantity_unit_id")));
        selectQuantityUnit.selectByVisibleText(product.getQuantityUnit());
        Select selectDeliveryStatus = new Select(driver.findElement(By.name("delivery_status_id")));
        selectDeliveryStatus.selectByVisibleText(product.getDeliveryStatus());
        Select selectSoldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        selectSoldOutStatus.selectByVisibleText(product.getSoldOutStatus());
        File photo = new File(product.getPhoto());
        driver.findElement(By.name("new_images[]")).sendKeys(photo.getAbsolutePath());
        driver.findElement(By.name("date_valid_from")).sendKeys(product.getDateValidFrom());
        driver.findElement(By.name("date_valid_to")).sendKeys(product.getDateValidTo());
    }

    private void fillInformationProductPage(Product product) {
        gotoPageInProductMenu("Information");
        Select selectManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        selectManufacturer.selectByVisibleText(product.getManufacturer());
        driver.findElement(By.name("keywords")).sendKeys(product.getKeywords());
        driver.findElement(By.name("short_description[en]")).sendKeys(product.getShortDescription());
        driver.findElement(By.className("trumbowyg-editor")).sendKeys(product.getDescription());
    }

    private void fillPriceProductPage(Product product) {
        gotoPageInProductMenu("Prices");
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys(product.getPurchasePrice());
        Select selectSoldOutStatus = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        selectSoldOutStatus.selectByVisibleText(product.getCurrency());
        driver.findElement(By.name("prices[USD]")).clear();
        driver.findElement(By.name("prices[USD]")).sendKeys(product.getPrice());
    }

    private void gotoPageInProductMenu(String menuTitle) {
        driver.findElement(By.xpath(String.format("//ul[@class='index']//a[.='%s']", menuTitle))).click();
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
            if (i != 0) {
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
