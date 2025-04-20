package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productItem = $(".product-card-list-slim--cart-item");
    private final SelenideElement increaseQuantityButton = $(".v-counter__arrow-button--plus");
    private final SelenideElement decreaseQuantityButton = $(".v-counter__arrow-button--minus");
    private final SelenideElement removeProductButton = $(".cart-product-item__remove button.product-remove-icon");
    private final SelenideElement emptyCartMessage = $(".cart__title--empty");
    private final SelenideElement productQuantityInput = $(".product-card-list-slim--cart-item .v-counter__input");

    @Step("Проверить, что добавленный товар '{productId}' в корзине")
    public void verifyProductInCart(String productId) {
        productItem.shouldBe(visible).shouldHave(text(productId));
    }

    @Step("Проверить, что количество товара в корзине равно {expectedQty}")
    public void verifyProductQuantity(int expectedQty) {
        productQuantityInput.shouldHave(value(String.valueOf(expectedQty)));
    }


    @Step("Нажать на +, чтобы увеличить количество товара в корзине")
    public CartPage increaseProductQuantity() {
        increaseQuantityButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на -, чтобы уменьшить количество товара в корзине")
    public CartPage decreaseProductQuantity() {
        decreaseQuantityButton.shouldBe(visible).click();
        return this;
    }


    @Step("Нажать на X у товара, чтобы удалить товар из корзины")
    public CartPage removeProductFromCart() {
        removeProductButton.click();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public void verifyCartIsEmpty() {
        emptyCartMessage.shouldBe(visible);
    }

}


















