package tests.api;

import api.models.AddToCartResponseModel;
import api.steps.UserApiSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

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

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode()).isEqualTo("inStock");
            softly.assertThat(response.getQuantityAdded()).isGreaterThanOrEqualTo(1);
            softly.assertThat(response.getEntry().getProduct().getCode()).isEqualTo(validProduct);
            softly.assertThat(response.getEntry().getProduct().getInCart()).isTrue();

            softly.assertThat(response.getEntry().getProduct().getStock().getStockLevel())
                    .isNotNull()
                    .isGreaterThan(0);
            softly.assertThat(response.getEntry().getProduct().getStock().getStockStatusText())
                    .isNotEmpty();

            softly.assertThat(response.getEntry().getProduct().getPrice().getCurrencyIso())
                    .isEqualTo("RUB");
            softly.assertThat(response.getEntry().getProduct().getPrice().getValue())
                    .isNotNull()
                    .isGreaterThan(0.0);
            softly.assertThat(response.getEntry().getProduct().getPrice().getFormattedValue())
                    .isNotEmpty();
            softly.assertThat(response.getEntry().getProduct().getPrice().getPriceType())
                    .isEqualTo("BUY");
        });
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("API: Добавление несуществующего товара в корзину")
    @DisplayName("Проверка добавления несуществующего товара в корзину")
    public void addBadProductToCartTest() {

        AddToCartResponseModel response = new UserApiSteps().addBadProductToCart(invalidProduct, 1, cookies);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getErrors()).isNotNull();
            softly.assertThat(response.getErrors().size()).isGreaterThan(0);
            softly.assertThat(response.getErrors().get(0).getMessage())
                    .isEqualTo("Товара с артикулом " + invalidProduct + " нет в наличии.");
        });

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
