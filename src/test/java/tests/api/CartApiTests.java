package tests.api;

import api.models.AddToCartResponseModel;
import api.steps.UserApiSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TestData.getTestData;

@DisplayName("API тесты на добавление товара в корзину")
@Tag("API")
public class CartApiTests extends TestBaseApi {
    private final String validProduct = getTestData("addProduct");
    private final String invalidProduct = getTestData("unknownProduct");

    @BeforeEach
    void clearCartBefore() {
        new UserApiSteps().clearCart(cookies);
    }

    @AfterEach
    void clearCartAfter() {
        new UserApiSteps().clearCart(cookies);
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("API: Добавление товара в корзину")
    @DisplayName("Проверка добавления товара в корзину")
    public void addProductToCartTest() {

        AddToCartResponseModel response = new UserApiSteps().addProductToCart(validProduct, 1, cookies);

        assertThat(response.getStatusCode()).isEqualTo("inStock");
        assertThat(response.getQuantityAdded()).isGreaterThanOrEqualTo(1);
        assertThat(response.getEntry().getProduct().getCode()).isEqualTo(validProduct);
        assertThat(response.getEntry().getProduct().getInCart()).isTrue();
        assertThat(response.getEntry().getProduct().getStock().getStockLevel()).isNotNull().isGreaterThan(0);
        assertThat(response.getEntry().getProduct().getStock().getStockStatusText()).isNotEmpty();
        assertThat(response.getEntry().getProduct().getPrice().getCurrencyIso()).isEqualTo("RUB");
        assertThat(response.getEntry().getProduct().getPrice().getValue()).isNotNull().isGreaterThan(0.0);
        assertThat(response.getEntry().getProduct().getPrice().getFormattedValue()).isNotEmpty();
        assertThat(response.getEntry().getProduct().getPrice().getPriceType()).isEqualTo("BUY");
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("API: Добавление несуществующего товара в корзину")
    @DisplayName("Проверка добавления несуществующего товара в корзину")
    public void addBadProductToCartTest() {

        AddToCartResponseModel response = new UserApiSteps().addBadProductToCart(invalidProduct, 1, cookies);

        assertThat(response.getErrors()).isNotNull();
        assertThat(response.getErrors().size()).isGreaterThan(0);
        assertThat(response.getErrors().get(0).getMessage()).isEqualTo("Товара с артикулом " + invalidProduct + " нет в наличии.");

    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("API: Очистка корзины")
    @DisplayName("Проверка очистки корзины")
    public void clearCartTest() {

        UserApiSteps testStepsApi = new UserApiSteps();
        testStepsApi.clearCart(cookies);

    }

}
