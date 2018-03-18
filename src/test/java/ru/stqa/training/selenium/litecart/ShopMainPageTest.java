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
}
