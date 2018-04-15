package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.litecart.model.User;

public class RegistrationTest extends TestBase {

    @Test
    public void testNewUserRegistration() {
        app.driver.get("http://localhost/litecart/en/");
        app.driver.findElement(By.cssSelector("form[name=login_form] a")).click();
        User user = new User().withFirstname("John").withLastname("Kruger").withAddress("US, Maine, Augusta").withPostcode("92056").
                withCity("Augusta").withCountry("US").withState("Maine").withEmail("john" + String.valueOf(System.currentTimeMillis()) + "@gmail.com").
                withPhone("13235091").withPassword("admin");
        fillCreateAccountForm(user);
        confirmUserCreation();
        userLogout();
        userLogin(user.getEmail(), user.getPassword());
        userLogout();
    }




    private void userLogin(String email, String password) {
        app.driver.findElement(By.cssSelector("form[name='login_form'] input[name='email']")).sendKeys(email);
        app.driver.findElement(By.cssSelector("form[name='login_form'] input[name='password']")).sendKeys(password);
        app.driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    private void userLogout() {
        app.driver.findElement(By.linkText("Logout")).click();
    }

    private void fillCreateAccountForm(User user) {
        app.driver.findElement(By.name("firstname")).sendKeys(user.getFirstname());
        app.driver.findElement(By.name("lastname")).sendKeys(user.getLastname());
        app.driver.findElement(By.name("address1")).sendKeys(user.getAddress());
        app.driver.findElement(By.name("postcode")).sendKeys(user.getPostcode());
        app.driver.findElement(By.name("city")).sendKeys(user.getCity());
        Select selectCountry = new Select(app.driver.findElement(By.name("country_code")));
        selectCountry.selectByValue(user.getCountry());
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name=zone_code]")));
        Select selectState = new Select(app.driver.findElement(By.cssSelector("select[name=zone_code]")));
        selectState.selectByVisibleText(user.getState());
        app.driver.findElement(By.name("email")).sendKeys(user.getEmail());
        app.driver.findElement(By.name("phone")).sendKeys(user.getPhone());
        app.driver.findElement(By.name("password")).sendKeys(user.getPassword());
        app.driver.findElement(By.name("confirmed_password")).sendKeys(user.getPassword());
    }

    private void confirmUserCreation() {
        app.driver.findElement(By.cssSelector("button[name=create_account]")).click();
    }
}
