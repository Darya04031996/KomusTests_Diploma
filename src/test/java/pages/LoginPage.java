package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends TestBase {

    private final SelenideElement emailField = $("[placeholder='E-mail']");
    private final SelenideElement passwordField = $("[placeholder='Пароль']");
    private final SelenideElement loginButton = $("button[type=submit]");
    private final SelenideElement loginForm = $(".main-info-profile__name");
    private SelenideElement passwordErrorMessage = $(".v-form-error--start");
    private SelenideElement emailErrorMessage = $(".v-form-error");
    private SelenideElement pageTitle = $("h4[data-test-id='page-title']");
    private SelenideElement captchaImage = $(".v-captcha__image");
    private SelenideElement captchaInput = $("input[name='captcha']");

    public LoginPage openPage() {
        open("/login");
        $(".auth-form-group").shouldBe(visible);
        return this;
    }

    public LoginPage loginWithEmailAndPassword(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        loginButton.click();
        return this;
    }
    @Step("Проверить, что пользователь залогинен как Дарья Михайловна Мельгунова")
    public LoginPage checkUserIsLoggedIn() {
        $(".main-info-profile").shouldBe(visible);
        loginForm.shouldHave(text("Дарья Михайловна Мельгунова"));
        return this;
    }

    @Step("Проверить, что отобразилось уведомление 'Неверно введен пароль'")
    public LoginPage checkEmailError() {
        passwordErrorMessage.shouldHave(text("Неверно введен пароль"));
        return this;
    }
    @Step("Проверить, что отобразилось уведомление 'Пользователь с данным E-mail не найден. Проверьте адрес'")
    public LoginPage checkPasswordError() {
        emailErrorMessage.shouldHave(text("Пользователь с данным E-mail не найден. Проверьте адрес"));
        return this;
    }

    @Step("Проверить, что пользователь остался на странице логина")
    public LoginPage verifyUserStayedOnLoginPage() {
        pageTitle.shouldHave(text("Вход"));
        return this;
    }
    @Step("Проверить, что под полем Логин - валидационное сообщение 'Это поле необходимо заполнить'")
    public void checkUserIsNotLoggedInEmptyLogin() {
        emailField.sibling(0).shouldHave(text("Это поле необходимо заполнить"));
    }
    @Step("Проверить, что под полем Пароль - валидационное сообщение 'Это поле необходимо заполнить'")
        public void checkUserIsNotLoggedInEmptyPassword() {
        passwordField.sibling(1).shouldHave(text("Это поле необходимо заполнить"));
    }
    @Step("Проверить, что капча появилась после нескольких неверных попыток")
    public LoginPage verifyCaptchaAppears() {
        captchaImage.shouldBe(visible);
        captchaInput.shouldBe(visible);
        return this;
    }

}
