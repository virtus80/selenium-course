package ru.stqa.training.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class ShopMainPageTest extends TestBase{

    @Test
    public void testStickerOnImage() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> images = driver.findElements(By.cssSelector("li.product"));
        for(WebElement image : images) {
            List<WebElement> stickers = image.findElements(By.cssSelector("div.sticker"));
            assertEquals(stickers.size(), 1);
        }
    }

    @Test
    public void testCompareProductPage() {
        driver.get("http://localhost/litecart/en/");
        WebElement productOnManePage = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String productNameOnManePage = getProductAttribute(productOnManePage, "name");
        String regularPriceOnManePage = getProductAttribute(productOnManePage, "regular-price");
        String campaignPriceOnManePage = getProductAttribute(productOnManePage, "campaign-price");
        productOnManePage.click();
        WebElement productOnProductPage = driver.findElement(By.cssSelector("#box-product"));
        String productNameOnProductPage = getProductAttribute(productOnProductPage, "title");
        String regularPriceOnProductPage = getProductAttribute(productOnProductPage, "regular-price");
        String campaignPriceOnProductPage = getProductAttribute(productOnProductPage, "campaign-price");
        assertEquals(productNameOnManePage, productNameOnProductPage);
        assertEquals(regularPriceOnManePage, regularPriceOnProductPage);
        assertEquals(campaignPriceOnManePage, campaignPriceOnProductPage);
    }

    private String getProductAttribute(WebElement element, String className) {
        return element.findElement(By.className(className)).getText();
    }


}
