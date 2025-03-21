package api.steps;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static api.specs.KomusSpec.requestSpec;
import static api.specs.KomusSpec.responseSpecWithStatusCode200;
import static io.restassured.RestAssured.given;

public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";

    @Step("Получить данные профиля")
    public Response getProfile(Map<String, String> cookies) {
        return given(requestSpec)
                .cookies(cookies) // Передаём куки авторизации
                .when()
                .get(PROFILE_URL)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract()
                .response();
    }
}


