package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

@DisplayName("Тесты на авторизацию через личный кабинет")
@Tag("WEB")
public class LoginTest extends TestBase{
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация с корректными данными")
    void successfulLoginTest() {

        loginPage
                .openPage()
                .enterEmail("3333@mail.ru")
                .enterPassword("8468642")
                .clickLoginButton();

    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    void incorrectPasswordTest() {

        loginPage
                .openPage()
                .enterEmail("3333@mail.ru")
                .enterPassword("wrongpass")
                .clickLoginButton();
    }

    @Test
    @DisplayName("Авторизация с несуществующей почтой")
    void nonExistingEmailTest() {

        loginPage
                .openPage()
                .enterEmail("fakeuser@mail.com")
                .enterPassword("8468642")
                .clickLoginButton();
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    void emptyFieldsTest() {

        loginPage
                .openPage()
                .clickLoginButton();
    }

}
