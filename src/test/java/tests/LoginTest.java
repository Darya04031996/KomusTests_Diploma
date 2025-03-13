package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("UI тесты на авторизацию")
@Tag("WEB")
public class LoginTest extends TestBase {
    private final LoginPage loginPage = new LoginPage();

    @Test
    public void successfulLoginTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "BestLife2025")
                .checkUserIsLoggedIn();
    }

    @Test
    public void incorrectPasswordTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "BestLife2024")
                .checkPasswordError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    public void incorrectEmailTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword("wrongemail@gmail.com", "BestLife2025")
                .checkEmailError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    public void emptyLoginFieldTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword("", "somePassword")
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    public void emptyPasswordFieldTest() {
        loginPage.openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "")
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    public void captchaAppearsAfterMultipleFailedAttempts() {
        loginPage.openPage();
        for (int i = 0; i < 4; i++) {
            loginPage.loginWithEmailAndPassword("darya.melgunova@gmail.com", "wrongPassword");
        }
        loginPage.verifyCaptchaAppears();
    }
}