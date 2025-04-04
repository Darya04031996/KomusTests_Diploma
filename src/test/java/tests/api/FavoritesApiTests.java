package tests.api;


import api.models.FavoriteListResponseModel;
import api.models.FavoriteResponseModel;
import api.steps.AuthApi;
import api.steps.TestStepsApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TestData.getTestData;

@DisplayName("API тесты на добавление товара в избранное")
@Tag("API")
public class FavoritesApiTests extends TestBaseApi {

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Добавление товара в избранное")
    @DisplayName("Проверка добавления товара в избранное")

    public void addProductToFavoritesTest() {

        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login(TestBaseApi.credentialsConfig.username(), TestBaseApi.credentialsConfig.password());
        final String productCode = getTestData("favoriteProduct");
        FavoriteResponseModel response = new TestStepsApi().addProductToFavorites(productCode, cookies);
        assertThat(response.getFavoritesCount()).isGreaterThan(0);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка избранных товаров")
        public void getFavoriteProductsTest() {
            AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login(TestBaseApi.credentialsConfig.username(), TestBaseApi.credentialsConfig.password());

            FavoriteListResponseModel response = new TestStepsApi().getFavoriteProducts(cookies);
            assertThat(response).isNotNull();
            assertThat(response.getPayload().get(0).getCode()).isNotEmpty();
        }
    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Удаление товара из избранного")
    @DisplayName("Проверка удаления товара из избранного")
    public void removeProductFromFavoritesTest() {

        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login(TestBaseApi.credentialsConfig.username(), TestBaseApi.credentialsConfig.password());

        final String productCode = getTestData("favoriteProduct");

        new TestStepsApi().addProductToFavorites(productCode, cookies);
        int favoritesCount = new TestStepsApi().removeProductFromFavorites(productCode, cookies);
        assertThat(favoritesCount).isEqualTo(0);
    }

}
