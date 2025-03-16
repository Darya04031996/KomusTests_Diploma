package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import static common.TestData.getTestData;

public class CartTests extends TestBase {
    final ProductPage productPage = new ProductPage();
    final CartPage cartPage = new CartPage();
    final String product = getTestData("product");

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void checkProductAddingToCartTest() {
        productPage
                .openPage(product)
                .addToCart()
                .openCart();
        cartPage.verifyProductInCart(product);
    }

}
