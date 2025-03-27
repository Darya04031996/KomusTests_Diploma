package api.steps;
import api.models.AddToCartRequestModel;
import api.models.CartModificationResponseModel;
import api.models.ProfileApiModel;
import api.models.ProfileResponseModel;
import api.specs.KomusSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";
    private static final String CART_ADD_URL = "/api/cart/add";
    private static final String CART_UPDATE_URL = "/api/cart/change";
    private static final String CART_CLEAR_URL = "/api/cart/clear";

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

    @Step("Добавить товар '{productCode}' в корзину с количеством {qty}")
    public  CartModificationResponseModel addProductToCart (Map<String, String> cookies, String productCodePost, int qty) {
        AddToCartRequestModel requestData = new AddToCartRequestModel();
        requestData.setProductCodePost(productCodePost);
        requestData.setRefererCartAddedPage("product_page");
        requestData.setFrom("katalog");
        requestData.setQty(qty);

        Response response = given(KomusSpec.requestSpec)
                .contentType("application/json")
                .cookies(cookies)
                .body(requestData)
                .when()
                .post(CART_ADD_URL)
                .then()
                .spec(KomusSpec.responseSpecWithStatusCode200)
                .extract()
                .response();

        return response.as(CartModificationResponseModel.class);

    }
}







