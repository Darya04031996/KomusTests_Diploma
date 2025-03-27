package tests.api;

import api.models.CartModificationResponseModel;
import api.auth.AuthApi;
import api.steps.TestStepsApi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;


import static common.TestData.getTestData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API тесты на добавление товара в корзину")
@Tag("API")
public class CartApiTests extends TestBaseApi{

    @Test
    @DisplayName("Добавление товара в корзину")
    public void testAddProductToCart()  {
        final String productCodePost = getTestData("product");
        int qty = 1;

        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login("darya.melgunova@gmail.com", "BestLife2025");

        TestStepsApi testStepsApi = new TestStepsApi();

        CartModificationResponseModel modificationResponseModel = testStepsApi.addProductToCart(cookies, productCodePost, qty);

        assertThat(modificationResponseModel).isNotNull();
        assertThat(modificationResponseModel.getStatusCode()).isEqualTo("200");
        assertThat(modificationResponseModel.getQuantityAdded()).isEqualTo(qty);
        assertThat(modificationResponseModel.getEntry()).isNotNull();
        assertThat(modificationResponseModel.getEntry().getProduct().getCode()).isEqualTo(productCodePost);
        assertThat(modificationResponseModel.getMindboxCartData()).isNotNull();
    }

}
