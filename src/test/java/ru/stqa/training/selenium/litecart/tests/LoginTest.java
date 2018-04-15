package ru.stqa.training.selenium.litecart.tests;

import org.junit.Test;


public class LoginTest extends TestBase {

    @Test
    public void testLogin() {
        app.driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
    }

}
