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
        final String productCode = getTestData("product");
        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        AddToCartResponseModel response = new TestStepsApi().addProductToCart(productCode, 1, cookies);

        // Проверки состояния ответа
        assertThat(response.getStatusCode()).isEqualTo("inStock");
        assertThat(response.getQuantityAdded()).isEqualTo(1);

        // Проверки данных о товаре
        assertThat(response.getEntry().getProduct().getCode()).isEqualTo(productCode);
        assertThat(response.getEntry().getProduct().getInCart()).isTrue();

        // Проверка stockLevel
        assertThat(response.getEntry().getProduct().getStock().getStockLevel()).isEqualTo(5029);

        // Проверка статус текста
        assertThat(response.getEntry().getProduct().getStock().getStockStatusText())
                .contains("Доставка завтра");

        // Проверка цены
        assertThat(response.getEntry().getProduct().getPrice().getCurrencyIso()).isEqualTo("RUB");
        assertThat(response.getEntry().getProduct().getPrice().getValue()).isEqualTo(70.20);
        assertThat(response.getEntry().getProduct().getPrice().getFormattedValue()).isEqualTo("70,20 p.");
        assertThat(response.getEntry().getProduct().getPrice().getPriceType()).isEqualTo("BUY");

        // Другие проверки
        assertThat(response.getEntry().getProduct().getPrice().getCoins()).isEqualTo(0.0);
        assertThat(response.getEntry().getProduct().getPrice().getPriceRegionId()).isEqualTo("0");
    }
}
