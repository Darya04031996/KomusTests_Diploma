package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productItem = $(".product-card-list-slim--cart-item");
    private final SelenideElement productQuantity = $(".v-counter__input");
    private final SelenideElement increaseQuantityButton = $(".v-counter__arrow-button--plus");
    private final SelenideElement decreaseQuantityButton = $(".v-counter__arrow-button--minus");
    private final SelenideElement removeProductButton = $(".remove-icon");
    private final SelenideElement emptyCartMessage = $(".cart__title--empty");

    @Step("Проверить, что добавленный товар '{productId}' в корзине")
    public CartPage verifyProductInCart(String productId) {
        productItem.shouldBe(visible).shouldHave(text(productId));
        return this;
    }

    @Step("Проверить кол-во товара в корзине - {qty}")
    public CartPage verifyProductQuantity(String qty) {
        productQuantity.shouldHave(value(qty));
        return this;
    }

    @Step("Нажать на +, чтобы увеличить количество товара в корзине")
    public CartPage increaseProductQuantity() {
        increaseQuantityButton.click();
        return this;
    }

    @Step("Нажать на -, чтобы уменьшить количество товара в корзине")
    public CartPage decreaseProductQuantity() {
        decreaseQuantityButton.click();
        return this;
    }

    @Step("Нажать на X у товара, чтобы удалить товар из корзины")
    public CartPage removeProductFromCart() {
        removeProductButton.click();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public CartPage verifyCartIsEmpty() {
        emptyCartMessage.shouldBe(visible);
        return this;
    }
}


















