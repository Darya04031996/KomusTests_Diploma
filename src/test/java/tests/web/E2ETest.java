package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestData;

import static utils.TestData.getTestData;

@DisplayName("E2E тесты")
@Tag("WEB")
public class E2ETest extends TestBase {

    final ProductPage productPage = new ProductPage();
    final CartPage cartPage = new CartPage();
    final LoginPage loginPage = new LoginPage();
    final String product = getTestData("favoriteProduct");

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Полный пользовательский флоу: от избранного до корзины")
    @Story("UI: Сквозной сценарий добавления товара в избранное и корзину")
    @DisplayName("E2E: Добавить товар в избранное и корзину, затем удалить из корзины")
    public void endToEndAddToFavoritesAndCartFlowTest() {

        loginPage.openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserIsLoggedIn();

        productPage.openPage(product)
                .checkProductSku(product)
                .addToFavorites()
                .openFavoritesList()
                .checkProductInFavorites(product);

        productPage.openPage(product)
                .addToCart()
                .checkProductInCart();

        productPage.openCart();
        cartPage.removeProductFromCart()
                .verifyCartIsEmpty();
    }
}