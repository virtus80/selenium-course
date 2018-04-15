package ru.stqa.training.selenium.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "options[Size]")
    public WebElement sizeSelector;

    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    @FindBy(css = "span.quantity")
    public WebElement quantityItems;


    public ProductPage addProductToBucket() {
        int numBeforeAdding = getProductQuantityInBucket();
        if (driver.findElements(By.name("options[Size]")).size() > 0) { selectSize(); }
        addToCartButton.click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(numBeforeAdding + 1)));
        return this;
    }

    public void gotoBucket() {
        driver.findElement(By.linkText("Checkout »")).click();
    }

    private int getProductQuantityInBucket() {
        return Integer.parseInt(quantityItems.getText());
    }

    private void selectSize() {
        Select selectSize = new Select(sizeSelector);
        selectSize.selectByIndex(1);
    }
}
