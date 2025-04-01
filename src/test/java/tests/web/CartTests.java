package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import static common.TestData.getTestData;

@DisplayName("UI тесты на корзину")
@Tag("WEB")
public class CartTests extends TestBase {
    final ProductPage productPage = new ProductPage();
    final CartPage cartPage = new CartPage();
    final String product = getTestData("addProduct");

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления товара в корзину")
    public void checkProductAddingToCartTest() {
        productPage
                .openPage(product)
                .addToCart()
                .checkProductInCart()
                .openCart();
        cartPage.verifyProductInCart(product);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Изменение товара в корзине")
    @DisplayName("Проверка увеличения количества товара в корзине")
    public void checkProductCountIncreasingInCartTest() {
        productPage
                .openPage(product)
                .addToCart()
                .checkProductInCart()
                .openCart();
        cartPage
                .increaseProductQuantity()
                .verifyProductQuantity(2);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Изменение товара в корзине")
    @DisplayName("Проверка уменьшения количества товара в корзине")
    public void checkProductCountDecreasingInCartTest() {
        productPage
                .openPage(product)
                .addToCart()
                .checkProductInCart()
                .openCart();
        cartPage
                .increaseProductQuantity()
                .decreaseProductQuantity()
                .verifyProductQuantity(1);
    }
    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Удаление товара из корзины")
    @DisplayName("Проверка удаления товара из корзины")
    public void checkProductDeletingFromCartTest() {
        productPage
                .openPage(product)
                .addToCart()
                .checkProductInCart()
                .openCart();
        cartPage
                .removeProductFromCart()
                .verifyCartIsEmpty();

    }


    }

