package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement emailField = $("[placeholder='E-mail']");
    private final SelenideElement passwordField = $("[placeholder='Пароль']");
    private final SelenideElement loginButton = $("button[type=submit]");
    private final SelenideElement loginForm = $(".main-info-profile__name");


    public LoginPage openPage() {
        open("/");
        $("a[href*='menu-g1-lichnyj_kabinet']").click();
        $(".auth-form-group").shouldBe(visible);
        return this;
    }

    public LoginPage loginWithEmailAndPassword(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        loginButton.click();
        return this;
    }
    @Step("Проверить, что залогинен как Дарья Михайловна Мельгунова")
    public LoginPage checkUserIsLoggedIn() {
        $(".main-info-profile").shouldBe(visible);
        loginForm.shouldHave(text("Дарья Михайловна Мельгунова"));
        return this;
    }
    @Step("Проверить, что пользователь не залогинен, остался на странице логина")
    @Step("Проверить, что отобразилось уведомление 'Captcha validate error'")
    @Step("Проверить, что под полем Логин - валидационное сообщение 'Поле обязательно для заполнения'")
    @Step("Проверить, что под полем Пароль - валидационное сообщение 'Поле обязательно для заполнения'")
    @Step("Проверить, что отобразилось уведомление про неккоректные данные")
}
