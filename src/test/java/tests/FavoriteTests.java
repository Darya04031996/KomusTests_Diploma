package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductPage;

import static common.TestData.getTestData;

public class FavoriteTests extends TestBase{
    final ProductPage productPage = new ProductPage();
    final String product = getTestData("product");

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void addProductToFavoritesTest() {
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
        productPage
                .openPage(product)
                .addToFavorites()
                .openFavoritesList()
                .removeFromFavorites()
                .checkFavoritesIsEmpty();
}

}
