package tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBaseApi {
    @BeforeAll
    static void configParams() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://www.komus.ru/");
    }

    @BeforeEach
    void addSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

}

