package tests.api;

import api.steps.AuthApiSteps;
import config.CredentialsConfig;
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
        cookies = new AuthApiSteps().login(
                credentialsConfig.getEmail(),
                credentialsConfig.getPassword()
        );
    }
}

