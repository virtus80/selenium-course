package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;

public class ProductsInBucketTest extends TestBase {

    @Test
    public void testAddDeleteProductsInBucket() {
        for (int i = 1; i<=3; i++) {
            app.mainPage.open().gotoProductPage();
            app.productPage.addProductToBucket();
        }
        app.productPage.gotoBucket();
        app.bucketPage.deleteAllProductsFromBucket();
    }
}
