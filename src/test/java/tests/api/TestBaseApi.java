package tests.api;

import api.steps.AuthApi;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

public class TestBaseApi {

    public static CredentialsConfig credentialsConfig =
            ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    protected Map<String, String> cookies;

    @BeforeAll
    static void configParams() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://www.komus.ru/");
    }

    @BeforeEach
    void loginUser() {
        cookies = new AuthApi().login(
                credentialsConfig.username(),
                credentialsConfig.password()
        );
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}

