package api.auth;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tests.api.TestBaseApi;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi extends TestBaseApi {
    private static final String LOGIN_URL = "https://www.komus.ru/api/login";
    private Map<String, String> cookies = new HashMap<>();

    public void login(String username, String password) {
        Response response = given()
                .contentType(ContentType.URLENC)
                .queryParams("username", username)
                .queryParams("password", password)
                .when()
                .post(LOGIN_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        cookies = response.getCookies();
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
