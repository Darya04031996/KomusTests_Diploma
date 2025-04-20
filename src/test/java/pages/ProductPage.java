package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.ElementClickInterceptedException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement productTitle = $("h1.product-details-page__title");
    private final SelenideElement productSku = $(".qa-vendor-code");
    private final SelenideElement addToFavoritesButton = $("a.add-to-favorite__link");
    private final SelenideElement cartButton = $(".js-product-add.js-gtm--addtocart");
    private final SelenideElement deleteFromFavoritesButton = $("button.product-remove-icon");
    private final SelenideElement emptyFavoritesMessage = $("h6.page-empty__title");
    private final SelenideElement goToFavoritesButton = $("a.favorite-counter__link");
    private final SelenideElement addedToCartButton = $("input.js-edit-count-in-cart[value='Изменить']");


    @Step("Открыть страницу товара '{productId}'")
    public ProductPage openPage(String productId) {
        open("https://www.komus.ru/katalog/tekhnika/kompyutery-i-periferiya/periferijnye-ustrojstva/myshi/myshi-besprovodnye/mysh-besprovodnaya-logitech-m171-cherno-seraya-910-004643-/p/" + productId + "/?text=" + productId);
        productTitle.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что артикул товара соответствует '{expectedSku}'")
    public ProductPage checkProductSku(String expectedSku) {
        productSku.shouldHave(text(expectedSku));
        return this;
    }

    @Step("Нажать на сердечко у товара, чтобы добавить товар в Избранное")
    public ProductPage addToFavorites() {
        addToFavoritesButton.click();
        return this;
    }

    @Step("Нажать на сердечко, чтобы открыть список избранных товаров")
    public ProductPage openFavoritesList() {
        goToFavoritesButton.click();
        return this;
    }

    @Step("Проверить, что товар '{productId}' добавлен в Избранное")
    public ProductPage checkProductInFavorites(String productId) {
        $("a[href*='/p/" + productId + "/']").shouldBe(visible);
        return this;
    }

    @Step("Нажать X у товара, чтобы удалить из Избранного")
    public ProductPage removeFromFavorites() {
        deleteFromFavoritesButton.click();
        return this;
    }

    @Step("Проверить, что список избранных товаров пуст")
    public void checkFavoritesIsEmpty() {
        emptyFavoritesMessage.shouldHave(text("Здесь пока ничего нет"));
    }

    @Step("Добавить товар в корзину")
    public ProductPage addToCart() {
        cartButton.scrollIntoView(true);
        cartButton.shouldBe(visible, enabled);
        try {
            cartButton.click();
        } catch (ElementClickInterceptedException e) {
            executeJavaScript("arguments[0].click();", cartButton);
        }
        return this;
    }

    @Step("Открыть корзину")
    public void openCart() {
        open("/cart");
    }

    @Step("Проверить, что кнопка изменилась на 'Изменить'")
    public ProductPage checkProductInCart() {
        addedToCartButton.shouldBe(visible);
        return this;
    }
}

