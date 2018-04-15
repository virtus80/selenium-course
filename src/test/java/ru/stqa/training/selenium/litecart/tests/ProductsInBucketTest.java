package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;

public class ProductsInBucketTest extends TestBase {

    @Test
    public void testAddDeleteProductsInBucket() {
        for (int i = 1; i<=3; i++) {
            mainPage.open().gotoProductPage();
            productPage.addProductToBucket();
        }
        productPage.gotoBucket();
        bucketPage.deleteAllProductsFromBucket();
    }
}
