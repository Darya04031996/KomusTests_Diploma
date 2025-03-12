package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на авторизацию через личный кабинет")
@Tag("WEB")
public class LoginTest {
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация с корректными данными")
    void successfulLoginTest() {

        loginPage
                .openPage()
                .enterEmail("3333@mail.ru")
                .enterPassword("8468642")
                .clickLoginButton();

        $(".user-profile").shouldBe(visible); // Проверяем, что пользователь авторизован
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    void incorrectPasswordTest() {

        loginPage
                .openPage()
                .enterEmail("3333@mail.ru")
                .enterPassword("wrongpass")
                .clickLoginButton();

        $(".v-form-error").shouldHave(text("Неверный логин или пароль"));
    }

    @Test
    @DisplayName("Авторизация с несуществующей почтой")
    void nonExistingEmailTest() {

        loginPage
                .openPage()
                .enterEmail("fakeuser@mail.com")
                .enterPassword("8468642")
                .clickLoginButton();

        $(".v-form-error").shouldHave(text("Пользователь не найден"));
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    void emptyFieldsTest() {

        loginPage
                .openPage()
                .clickLoginButton();

        $(".v-form-error").shouldHave(text("Заполните все обязательные поля"));
    }

}
