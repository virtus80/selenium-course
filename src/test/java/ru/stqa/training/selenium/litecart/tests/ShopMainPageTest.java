package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ShopMainPageTest extends TestBase {

    @Test
    public void testStickerOnImage() {
        app.driver.get("http://localhost/litecart/en/");
        List<WebElement> images = app.driver.findElements(By.cssSelector("li.product"));
        for (WebElement image : images) {
            List<WebElement> stickers = image.findElements(By.cssSelector("div.sticker"));
            assertEquals(stickers.size(), 1);
        }
    }

    @Test
    public void testCompareProductPage() {
        app.driver.get("http://localhost/litecart/en/");
        WebElement productOnMainPage = app.driver.findElement(By.cssSelector("#box-campaigns li.product"));
        WebElement productNameOnMainPage = getProductAttribute(productOnMainPage, "name");
        WebElement regularPriceOnMainPage = getProductAttribute(productOnMainPage, "regular-price");
        WebElement campaignPriceOnMainPage = getProductAttribute(productOnMainPage, "campaign-price");
        String textProductNameOnMainPage = productNameOnMainPage.getText();
        String textRegularPriceOnMainPage = regularPriceOnMainPage.getText();
        String textCampaignPriceOnMainPage = campaignPriceOnMainPage.getText();
        //compare font style on main page
        assertEquals("s", regularPriceOnMainPage.getAttribute("localName"));
        assertEquals("strong", campaignPriceOnMainPage.getAttribute("localName"));
        //compare font color on main page
        String[] colorRegularPriceOnMainPage = getElementColorRgbArray(regularPriceOnMainPage);
        String[] colorCampaignPriceOnMainPage = getElementColorRgbArray(campaignPriceOnMainPage);
        assertEquals(colorRegularPriceOnMainPage[0], colorRegularPriceOnMainPage[1], colorRegularPriceOnMainPage[2]);
        assertEquals(colorCampaignPriceOnMainPage[1], colorCampaignPriceOnMainPage[2]);
        //compare font size on main page
        assertTrue(getFontSize(campaignPriceOnMainPage) > getFontSize(regularPriceOnMainPage));

        productOnMainPage.click();
        WebElement productOnProductPage = app.driver.findElement(By.cssSelector("#box-product"));
        WebElement productNameOnProductPage = getProductAttribute(productOnProductPage, "title");
        WebElement regularPriceOnProductPage = getProductAttribute(productOnProductPage, "regular-price");
        WebElement campaignPriceOnProductPage = getProductAttribute(productOnProductPage, "campaign-price");
        //compare text on main and product pages
        assertEquals(textProductNameOnMainPage, productNameOnProductPage.getText());
        assertEquals(textRegularPriceOnMainPage, regularPriceOnProductPage.getText());
        assertEquals(textCampaignPriceOnMainPage, campaignPriceOnProductPage.getText());
        //compare font style on product page
        assertEquals("s", regularPriceOnProductPage.getAttribute("localName"));
        assertEquals("strong", campaignPriceOnProductPage.getAttribute("localName"));
        //compare font color on product page
        String[] colorRegularPriceOnProductPage = getElementColorRgbArray(regularPriceOnProductPage);
        String[] colorCampaignPriceOnProductPage = getElementColorRgbArray(campaignPriceOnProductPage);
        assertEquals(colorRegularPriceOnProductPage[0], colorRegularPriceOnProductPage[1], colorRegularPriceOnProductPage[2]);
        assertEquals(colorCampaignPriceOnProductPage[1], colorCampaignPriceOnProductPage[2]);
        //compare font size on product page
        assertTrue(getFontSize(campaignPriceOnProductPage) > getFontSize(regularPriceOnProductPage));
    }

    private WebElement getProductAttribute(WebElement element, String className) {
        return element.findElement(By.className(className));
    }

    private String[] getElementColorRgbArray(WebElement element) {
        String color = Color.fromString(element.getCssValue("color")).asRgb();
        return color.replaceAll("[rgb()]", "").split(", ");
    }

    private double getFontSize(WebElement element) {
        return Double.parseDouble(element.getCssValue("font-size").replace("px", ""));
    }
}
