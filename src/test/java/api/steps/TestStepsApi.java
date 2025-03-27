package api.steps;
import api.models.AddToCartRequestModel;
import api.models.AddToCartResponseModel;
import api.models.ProfileApiModel;
import api.models.ProfileResponseModel;
import api.specs.KomusSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static api.specs.KomusSpec.requestSpec;
import static api.specs.KomusSpec.responseSpecWithStatusCode200;
import static io.restassured.RestAssured.given;


public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";
    private static final String CART_ADD_URL = "/api/cart/add";
    private static final String CART_UPDATE_URL = "/api/cart/change";
    private static final String CART_CLEAR_URL = "/api/cart/clear";

    @Step("Получить данные профиля")
    public ProfileApiModel getProfile(Map<String, String> cookies) {
        Response response = given(requestSpec)
                .cookies(cookies)
                .when()
                .get(PROFILE_URL)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract()
                .response();

        ProfileResponseModel profileResponse = response.as(ProfileResponseModel.class);
        return profileResponse.getPayload();
    }

    @Step("Добавить товар '{productCode}' в корзину с количеством {qty}")
    public AddToCartResponseModel addProductToCart(String productCode, int quantity, Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .queryParam("productCode", productCode)
                .queryParam("qty", quantity)
                .when()
                .post(CART_ADD_URL)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(AddToCartResponseModel.class);
    }
}







