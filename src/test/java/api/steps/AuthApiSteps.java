package api.steps;

import api.BaseSpec;
import api.models.LoginRequestModel;
import io.qameta.allure.Step;
import tests.api.TestBaseApi;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApiSteps extends TestBaseApi {

    private static final String LOGIN_URL = "/api/login";

    @Step("Авторизация пользователя с email: {email}")
    public Map<String, String> login(String username, String password) {
        LoginRequestModel loginRequest = new LoginRequestModel(username, password);

        return given(BaseSpec.requestSpec)
                .contentType("application/json")
                .body(loginRequest)
                .when()
                .post(LOGIN_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract()
                .response().getCookies();
    }
}