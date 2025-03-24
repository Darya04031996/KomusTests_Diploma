package api.auth;

import api.models.LoginRequestModel;
import api.specs.KomusSpec;

import io.restassured.response.Response;
import tests.api.TestBaseApi;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi extends TestBaseApi {

    private static final String LOGIN_URL = "/api/login";

    public Map<String, String> login(String username, String password) {
        // Выполняем запрос на логин
        Response response = given(KomusSpec.requestSpec)
                .contentType("application/json")
                .body(new LoginRequestModel(username, password))
                .when()
                .post(LOGIN_URL)
                .then()
                .spec(KomusSpec.responseSpecWithStatusCode200)
                .extract()
                .response();

        // Извлекаем куки из ответа, которые будут использованы для авторизации в следующих запросах
        return response.getCookies();
    }
}