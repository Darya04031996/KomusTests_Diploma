package api.steps;
import api.models.ProfileApiModel;
import api.models.ProfileResponseModel;
import api.specs.KomusSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";

    @Step("Получить данные профиля")
    public ProfileApiModel getProfile(Map<String, String> cookies) {
        Response response = given(KomusSpec.requestSpec)
                .cookies(cookies)
                .when()
                .get(PROFILE_URL)
                .then()
                .spec(KomusSpec.responseSpecWithStatusCode200)
                .extract()
                .response();

        ProfileResponseModel profileResponse = response.as(ProfileResponseModel.class);
        return profileResponse.getPayload();
    }

    }



