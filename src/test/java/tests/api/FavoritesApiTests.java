package tests.api;


import api.models.FavoriteListResponseModel;
import api.models.FavoriteResponseModel;
import api.steps.UserApiSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TestData.getTestData;

@DisplayName("API тесты на добавление товара в избранное")
@Tag("API")
public class FavoritesApiTests extends TestBaseApi {

    private final String productCode = getTestData("favoriteProduct");

    @BeforeEach
    void setUpFavoriteProduct() {

        new UserApiSteps().addProductToFavorites(productCode, cookies);
    }

    @AfterEach
    void cleanFavoriteProduct() {
        new UserApiSteps().removeProductFromFavorites(productCode, cookies);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Добавление товара в избранное")
    @DisplayName("Проверка добавления товара в избранное")
    public void addProductToFavoritesTest() {
        FavoriteResponseModel response = new UserApiSteps().addProductToFavorites(productCode, cookies);
        assertThat(response.getFavoritesCount()).isGreaterThan(0);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка избранных товаров")
    public void getFavoriteProductsTest() {
        FavoriteListResponseModel response = new UserApiSteps().getFavoriteProducts(cookies);
        assertThat(response).isNotNull();
        assertThat(response.getPayload().get(0).getCode()).isNotEmpty();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация избранных товаров пользователя")
    @Story("API: Удаление товара из избранного")
    @DisplayName("Проверка удаления товара из избранного")
    public void removeProductFromFavoritesTest() {
        int favoritesCount = new UserApiSteps().removeProductFromFavorites(productCode, cookies);
        assertThat(favoritesCount).isEqualTo(0);
    }
}
