package ru.stqa.training.selenium.litecart;

import org.junit.Test;


public class LoginTest extends TestBase {

    @Test
    public void testLogin() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
    }

}
