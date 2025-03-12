package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

@DisplayName("UI тесты на авторизацию")
@Tag("WEB")
public class LoginTest extends TestBase{
    final LoginPage loginPage = new LoginPage();

    @Test
    void successfulLoginTest() {

        loginPage
                .openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "correctPassword")
                .checkUserIsLoggedIn();

    }

    @Test
    void incorrectPasswordTest() {

        loginPage
                .openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "wrongPassword")
                .checkEmailError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    void incorrectEmailTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword("wrongemail@gmail.com", "correctPassword")
                .checkPasswordError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    void emptyLoginFieldTest() {

        loginPage.openPage()
                .loginWithEmailAndPassword("", "somePassword");
        loginPage.checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    void emptyPasswordFieldTest() {

        loginPage.openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "");
        loginPage.checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    void captchaAppearsAfterMultipleFailedAttempts() {
        loginPage.openPage();
        for (int i = 0; i < 4; i++) {
            loginPage.loginWithEmailAndPassword("darya.melgunova@gmail.com", "wrongPassword");
        }
        loginPage.verifyCaptchaAppears();
    }
}