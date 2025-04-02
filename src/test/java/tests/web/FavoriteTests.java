package tests.web;

import utils.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductPage;

import static utils.TestData.getTestData;

@DisplayName("UI тесты на избранное")
@Tag("WEB")
public class FavoriteTests extends TestBase {
    final ProductPage productPage = new ProductPage();
    final LoginPage loginPage = new LoginPage();
    final String product = getTestData("favoriteProduct");

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void addProductToFavoritesTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword(TestData.getEmail(), TestData.getPassword())
                .checkUserIsLoggedIn();
        productPage.openPage(product)
                .checkProductSku(product)
                .addToFavorites()
                .openFavoritesList()
                .checkProductInFavorites(product);
    }
    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Добавление товара в список избранного")
    @DisplayName("Проверка удаления продукта из избранного")
    public void checkProductDeletingFromFavoritesTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword(TestData.getEmail(), TestData.getPassword())
                .checkUserIsLoggedIn();
        productPage
                .openPage(product)
                .checkProductSku(product)
                .addToFavorites()
                .openFavoritesList()
                .checkProductInFavorites(product)
                .removeFromFavorites()
                .checkFavoritesIsEmpty();
}

}
