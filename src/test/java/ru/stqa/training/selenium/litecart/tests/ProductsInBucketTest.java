package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductsInBucketTest extends TestBase {

    @Test
    public void testAddDeleteProductsInBucket() {
        for (int i = 1; i<=3; i++) {
            addProductToBucket();
        }
        gotoBucket();
        deleteAllProductsFromBucket();
    }



    private void addProductToBucket() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-most-popular img")).click();
        int num = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            Select selectSize = new Select(driver.findElement(By.name("options[Size]")));
            selectSize.selectByIndex(1);
        }
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(num + 1)));
    }

    private void deleteAllProductsFromBucket() {
        int itemsInTable = driver.findElements(By.cssSelector("table.dataTable td.item")).size();
        while (itemsInTable > 0) {
            if (itemsInTable > 1) driver.findElement(By.cssSelector("ul.shortcuts img")).click();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable td.item"), itemsInTable - 1));
            itemsInTable--;
        }
    }

    private void gotoBucket() {
        driver.findElement(By.linkText("Checkout »")).click();
    }
}
