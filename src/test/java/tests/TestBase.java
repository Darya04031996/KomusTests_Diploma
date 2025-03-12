package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;

public class TestBase {
    LoginPage loginPage = new LoginPage();
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "920x920";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://www.komus.ru/";
    }
}