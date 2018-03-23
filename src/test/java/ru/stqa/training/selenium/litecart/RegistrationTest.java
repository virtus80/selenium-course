package ru.stqa.training.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.litecart.model.User;

public class RegistrationTest extends TestBase {

    @Test
    public void testNewUserRegistration() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();
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
        driver.findElement(By.cssSelector("form[name='login_form'] input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("form[name='login_form'] input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    private void userLogout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    private void fillCreateAccountForm(User user) {
        driver.findElement(By.name("firstname")).sendKeys(user.getFirstname());
        driver.findElement(By.name("lastname")).sendKeys(user.getLastname());
        driver.findElement(By.name("address1")).sendKeys(user.getAddress());
        driver.findElement(By.name("postcode")).sendKeys(user.getPostcode());
        driver.findElement(By.name("city")).sendKeys(user.getCity());
        Select selectCountry = new Select(driver.findElement(By.name("country_code")));
        selectCountry.selectByValue(user.getCountry());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name=zone_code]")));
        Select selectState = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        selectState.selectByVisibleText(user.getState());
        driver.findElement(By.name("email")).sendKeys(user.getEmail());
        driver.findElement(By.name("phone")).sendKeys(user.getPhone());
        driver.findElement(By.name("password")).sendKeys(user.getPassword());
        driver.findElement(By.name("confirmed_password")).sendKeys(user.getPassword());
    }

    private void confirmUserCreation() {
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
    }
}
