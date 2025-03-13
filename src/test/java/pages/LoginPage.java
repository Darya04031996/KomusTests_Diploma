package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
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
    private final SelenideElement passwordErrorMessage = $(".v-form-error--start");
    private final SelenideElement emailErrorMessage = $(".v-form-error");
    private final SelenideElement pageTitle = $("h4[data-test-id='page-title']");
    private final SelenideElement captchaImage = $(".v-captcha__image");
    private final SelenideElement captchaInput = $("input[name='captcha']");
    private final SelenideElement emailField1 = $("[autocomplete='email']");

    public LoginPage openPage() {
        open("/login");
        $(".auth-form-group").shouldBe(visible);
        return this;
    }

    public LoginPage loginWithEmailAndPassword(String email, String password) {
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        emailField.sendKeys(Keys.DELETE);
        emailField.setValue(email);
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        passwordField.sendKeys(Keys.DELETE);
        passwordField.setValue(password);
        loginButton.click();
        return this;
    }
    @Step("Проверить, что пользователь залогинен как Дарья Михайловна Мельгунова")
    public void checkUserIsLoggedIn() {
        $(".main-info-profile").shouldBe(visible);
        loginForm.shouldHave(text("Дарья Михайловна Мельгунова"));

    }

    @Step("Проверить, что отобразилось уведомление 'Неверно введен пароль'")
    public void checkPasswordError() {
        passwordErrorMessage.shouldHave(text("Неверно введен пароль"));

    }
    @Step("Проверить, что отобразилось уведомление 'Пользователь с данным E-mail не найден. Проверьте адрес'")
    public void checkEmailError() {
        emailErrorMessage.shouldHave(text("Пользователь с данным E-mail не найден. Проверьте адрес"));
    }

    @Step("Проверить, что пользователь остался на странице логина")
    public void verifyUserStayedOnLoginPage() {
        pageTitle.shouldHave(text("Вход"));

    }
    @Step("Проверить, что под полем Логин - валидационное сообщение 'Это поле необходимо заполнить'")
    public void checkUserIsNotLoggedInEmptyLogin() {
        emailField.setValue("");
        loginButton.click();
        emailField.parent().shouldHave(text("Это поле необходимо заполнить"));
    }
    @Step("Проверить, что под полем Пароль - валидационное сообщение 'Это поле необходимо заполнить'")
    public LoginPage checkUserIsNotLoggedInEmptyPassword() {
    passwordField.setValue("");
    loginButton.click();
    passwordErrorMessage.shouldHave(text("Это поле необходимо заполнить"));

    return this;
    }
    @Step("Проверить, что капча появилась после нескольких неверных попыток")
    public void verifyCaptchaAppears() {
        captchaImage.shouldBe(visible);
        captchaInput.shouldBe(visible);

    }

}
