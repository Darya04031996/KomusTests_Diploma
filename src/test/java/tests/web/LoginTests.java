package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import utils.TestData;

import static utils.TestData.email1;
import static utils.TestData.password1;


@DisplayName("UI тесты на авторизацию")
@Tag("WEB")
public class LoginTests extends TestBase {

    final LoginPage loginPage = new LoginPage();

    @BeforeEach
    void openLoginPage() {
        loginPage.openPage();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Успешный логин")
    @DisplayName("Проверка успешного логина")
    public void successfulLoginTest() {
        loginPage
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserIsLoggedIn();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный пароль")
    @DisplayName("Проверка неуспешного входа из-за неправильного пароля")
    public void incorrectPasswordTest() {
        loginPage
                .loginWithEmailAndPassword(TestData.email, password1)
                .checkPasswordError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный Email")
    @DisplayName("Проверка неуспешного входа из-за неправильного Email")
    public void incorrectEmailTest() {
        loginPage
                .loginWithEmailAndPassword(email1, TestData.password)
                .checkEmailError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного входа из-за пустого Email")
    public void emptyLoginFieldTest() {
        loginPage
                .loginWithEmailAndPassword("", TestData.password)
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный пароль")
    @DisplayName("Проверка неуспешного входа из-за пустого пароля")
    public void emptyPasswordFieldTest() {
        loginPage
                .loginWithEmailAndPassword(TestData.email, "")
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Проверка captcha")
    @DisplayName("Проверка появления окна с captcha, если есть больше 1 неудачной попытки входа")
    public void captchaAppearsAfterMultipleFailedAttempts() {
        for (int i = 0; i < 5; i++) {
            loginPage.loginWithEmailAndPassword(TestData.email, TestData.password1);
        }
        loginPage.verifyCaptchaAppears();
    }

    @ParameterizedTest(name = "Неверный логин для Email {0} и пароля {1}")
    @CsvSource({
            "wrongemail@example.com, correctpassword",
            "correctemail@example.com, wrongpassword",
            "'', correctpassword",
            "correctemail@example.com, ''"
    })
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного входа с некорректными Email и/или Паролем")
    void incorrectLoginTest(String email, String password) {
        loginPage.loginWithEmailAndPassword(email, password);
        loginPage.verifyUserStayedOnLoginPage();
    }
}