package ru.stqa.training.selenium.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart/en/");
        return this;
    }

    @FindBy(css = "#box-most-popular img")
    public WebElement firstPopularImage;

    public void gotoProductPage() {
        firstPopularImage.click();
    }


}
