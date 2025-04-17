package api.steps;

import api.models.LoginRequestModel;
import api.BaseSpec;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tests.api.TestBaseApi;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApiSteps extends TestBaseApi {

    private static final String LOGIN_URL = "/api/login";

    @Step("Авторизация пользователя с email: {email}")
    public Map<String, String> login(String username, String password) {
        LoginRequestModel loginRequest = new LoginRequestModel();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Response response = given(BaseSpec.requestSpec)
                .contentType("application/json")
                .body(loginRequest)
                .when()
                .post(LOGIN_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract()
                .response();

        return response.getCookies();
    }
}