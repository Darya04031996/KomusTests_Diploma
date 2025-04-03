package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.TestData;

import static utils.TestData.*;


@DisplayName("UI тесты на авторизацию")
@Tag("WEB")
public class LoginTests extends TestBase {

    final LoginPage loginPage = new LoginPage();

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Успешный логин")
    @DisplayName("Проверка успешного логина")
    public void successfulLoginTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserIsLoggedIn();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный пароль")
    @DisplayName("Проверка неуспешного входа из-за неправильного пароля")
    public void incorrectPasswordTest() {
        loginPage.openPage()
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
        loginPage.openPage()
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
        loginPage.openPage()
                .loginWithEmailAndPassword("", TestData.password)
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный пароль")
    @DisplayName("Проверка неуспешного входа из-за пустого пароля")
    public void emptyPasswordFieldTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword(TestData.email, "")
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Реализация логина на сайте")
    @Story("UI: Проверка captcha")
    @DisplayName("Проверка появления окна с captcha, если есть больше 1 неудачной попытки входа")
    public void captchaAppearsAfterMultipleFailedAttempts() {
        loginPage.openPage();
        for (int i = 0; i < 5; i++) {
            loginPage.loginWithEmailAndPassword(TestData.email, TestData.password1);
        }
        loginPage.verifyCaptchaAppears();
    }
}