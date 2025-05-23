package api.steps;

import api.BaseSpec;
import api.models.*;
import io.qameta.allure.Step;

import java.util.Map;

import static api.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;

public class UserApiSteps {
    private static final String PROFILE_URL = "/api/profile";
    private static final String CART_ADD_URL = "/api/cart/add";
    private static final String CART_FAV_URL = "/api/favorites/add";
    private static final String FAVORITES_GET_LIST_URL = "/favorites/getProductFavoriteList";
    private static final String FAVORITES_REMOVE_URL = "/api/favorites/remove";
    private static final String CART_CLEAR_URL = "/cart/clear";

    @Step("Получить данные профиля")
    public ProfilePayload getProfile(Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .when()
                .get(PROFILE_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract()
                .as(ProfileResponseModel.class)
                .getPayload();
    }

    @Step("Добавить товар '{productCode}' в корзину с количеством {quantity}")
    public AddToCartResponseModel addProductToCart(String productCode, int quantity, Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .queryParam("productCode", productCode)
                .queryParam("qty", quantity)
                .when()
                .post(CART_ADD_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract().as(AddToCartResponseModel.class);
    }

    @Step("Добавить несуществующий товар '{productCode}' в корзину с количеством {qty}")
    public AddToCartResponseModel addBadProductToCart(String productCode, int quantity, Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .queryParam("productCode", productCode)
                .queryParam("qty", quantity)
                .when()
                .post(CART_ADD_URL)
                .then()
                .spec(BaseSpec.responseSpec(400))
                .extract().as(AddToCartResponseModel.class);
    }

    @Step("Добавить товар '{productCode}' в избранное")
    public FavoriteResponseModel addProductToFavorites(String productCode, Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .queryParam("productCodes", productCode)
                .when()
                .post(CART_FAV_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract().as(FavoriteResponseModel.class);
    }

    @Step("Получить список товаров в избранном")
    public FavoriteListResponseModel getFavoriteProducts(Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .when()
                .get(FAVORITES_GET_LIST_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract().as(FavoriteListResponseModel.class);
    }

    @Step("Удалить товар '{productCode}' из избранного")
    public int removeProductFromFavorites(String productCode, Map<String, String> cookies) {
        return given()
                .spec(requestSpec)
                .cookies(cookies)
                .queryParam("productCode", productCode)
                .when()
                .post(FAVORITES_REMOVE_URL)
                .then()
                .spec(BaseSpec.responseSpec(200))
                .extract().as(FavoriteResponseModel.class)
                .getFavoritesCount();
    }

    @Step("Очистить корзину")
    public void clearCart(Map<String, String> cookies) {
        given()
                .baseUri("https://komus.ru")
                .spec(requestSpec)
                .cookies(cookies)
                .when()
                .get(CART_CLEAR_URL)
                .then()
                .spec(BaseSpec.responseSpec(200));
    }
}








