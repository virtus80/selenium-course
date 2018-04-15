package ru.stqa.training.selenium.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BucketPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BucketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul.shortcuts img")
    public WebElement productShortcut;

    @FindBy(name = "remove_cart_item")
    public WebElement removeProductButton;

    public void deleteAllProductsFromBucket() {
        int itemsInTable = getProductNumberInTable();
        while (itemsInTable > 0) {
            if (itemsInTable > 1) productShortcut.click();
            removeProductButton.click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable td.item"), itemsInTable - 1));
            itemsInTable--;
        }
    }

    private int getProductNumberInTable() {
        return driver.findElements(By.cssSelector("table.dataTable td.item")).size();
    }
}
