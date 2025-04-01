package api.steps;
import api.models.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static api.specs.KomusSpec.*;
import static io.restassured.RestAssured.given;

public class TestStepsApi {
    private static final String PROFILE_URL = "/api/profile";
    private static final String CART_ADD_URL = "/api/cart/add";
    private static final String CART_FAV_URL = "/api/favorites/add";
    private static final String FAVORITES_GET_LIST_URL = "/favorites/getProductFavoriteList";
    private static final String FAVORITES_REMOVE_URL = "/api/favorites/remove";

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
                .spec(responseSpecWithStatusCode200)
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
                .spec(responseSpecWithStatusCode400)
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
                .spec(responseSpecWithStatusCode200)
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
                .spec(responseSpecWithStatusCode200)
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
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavoriteResponseModel.class)
                .getFavoritesCount();
    }

    }








