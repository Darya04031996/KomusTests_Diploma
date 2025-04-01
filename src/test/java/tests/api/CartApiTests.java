package tests.api;

import api.auth.AuthApi;
import api.models.AddToCartResponseModel;
import api.steps.TestStepsApi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;


import static common.TestData.getTestData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API тесты на добавление товара в корзину")
@Tag("API")
public class CartApiTests extends TestBaseApi {

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductToCartTest() {
        final String productCode = getTestData("addProduct");
        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        AddToCartResponseModel response = new TestStepsApi().addProductToCart(productCode, 1, cookies);

        assertThat(response.getStatusCode()).isEqualTo("inStock");
        assertThat(response.getQuantityAdded()).isGreaterThanOrEqualTo(1);
        assertThat(response.getEntry().getProduct().getCode()).isEqualTo(productCode);
        assertThat(response.getEntry().getProduct().getInCart()).isTrue();

        assertThat(response.getEntry().getProduct().getStock().getStockLevel()).isNotNull().isGreaterThan(0);

        assertThat(response.getEntry().getProduct().getStock().getStockStatusText()).isNotEmpty();

        assertThat(response.getEntry().getProduct().getPrice().getCurrencyIso()).isEqualTo("RUB");

        assertThat(response.getEntry().getProduct().getPrice().getValue()).isNotNull().isGreaterThan(0.0);
        assertThat(response.getEntry().getProduct().getPrice().getFormattedValue()).isNotEmpty();

        assertThat(response.getEntry().getProduct().getPrice().getPriceType()).isEqualTo("BUY");
    }

    @Test
    @DisplayName("Добавление несуществующего товара в корзину")
    public void addBadProductToCartTest() {
        final String productCode = getTestData("unknownProduct");
        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        AddToCartResponseModel response = new TestStepsApi().addBadProductToCart(productCode, 1, cookies);

        assertThat(response.getErrors()).isNotNull();
        assertThat(response.getErrors().size()).isGreaterThan(0);
        assertThat(response.getErrors().get(0).getMessage()).isEqualTo("Товара с артикулом " + productCode + " нет в наличии.");

    }
    @Test
    @DisplayName("Очистка корзины")
    public void clearCartTest() {
        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        TestStepsApi testStepsApi = new TestStepsApi();
        testStepsApi.clearCart(cookies);

    }

}
