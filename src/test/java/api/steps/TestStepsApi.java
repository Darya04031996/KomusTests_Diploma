package api.steps;
import api.models.ProfileApiModel;
import api.specs.KomusSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static api.specs.KomusSpec.requestSpec;
import static api.specs.KomusSpec.responseSpecWithStatusCode200;
import static io.restassured.RestAssured.given;

public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";

    @Step("Получить данные профиля")
    public ProfileApiModel getProfile(Map<String, String> cookies) {
        return given(KomusSpec.requestSpec)
                .cookies(cookies) // Передаём куки авторизации
                .when()
                .get(PROFILE_URL)
                .then()
                .spec(KomusSpec.responseSpecWithStatusCode200)
                .extract()
                .as(ProfileApiModel.class);
    }
}


