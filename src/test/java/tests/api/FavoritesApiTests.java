package tests.api;


import api.models.FavoriteListResponseModel;
import api.models.FavoriteResponseModel;
import api.steps.AuthApi;
import api.steps.TestStepsApi;
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
    @DisplayName("Добавление товара в избранное")
    public void addProductToFavoritesTest() {

        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");
        final String productCode = getTestData("favoriteProduct");
        FavoriteResponseModel response = new TestStepsApi().addProductToFavorites(productCode, cookies);
        assertThat(response.getFavoritesCount()).isGreaterThan(0);
    }

        @Test
        @DisplayName("Получение списка избранных товаров")
        public void getFavoriteProductsTest() {
            AuthApi authApi = new AuthApi();
            Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

            FavoriteListResponseModel response = new TestStepsApi().getFavoriteProducts(cookies);
            assertThat(response).isNotNull();
            assertThat(response.getPayload().get(0).getCode()).isNotEmpty();
        }
    @Test
    @DisplayName("Удаление товара из избранного")
    public void removeProductFromFavoritesTest() {

        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        final String productCode = getTestData("favoriteProduct");

        new TestStepsApi().addProductToFavorites(productCode, cookies);
        int favoritesCount = new TestStepsApi().removeProductFromFavorites(productCode, cookies);
        assertThat(favoritesCount).isEqualTo(0);
    }

}
