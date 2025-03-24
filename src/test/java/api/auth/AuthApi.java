package api.auth;

import api.models.LoginRequestModel;
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
        LoginRequestModel loginRequest = new LoginRequestModel(username, password);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post(LOGIN_URL)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();


        cookies = response.getCookies();
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}