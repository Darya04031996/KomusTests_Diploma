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
    public void successfulLoginTest() {

        loginPage
                .openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "correctPassword")
                .checkUserIsLoggedIn();

    }

    @Test
    public void incorrectPasswordTest() {

        loginPage
                .openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "wrongPassword")
                .checkEmailError();
                 .verifyUserStayedOnLoginPage();
    }

    @Test
    public void incorrectEmailTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword("wrongemail@gmail.com", "correctPassword")
                .checkPasswordError();
        loginPage.verifyUserStayedOnLoginPage();
    }

    @Test
    public void emptyLoginFieldTest() {

        loginPage.openPage()
                .loginWithEmailAndPassword("", "somePassword");
        loginPage.checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    public void emptyPasswordFieldTest() {

        loginPage.openPage()
                .loginWithEmailAndPassword("darya.melgunova@gmail.com", "");
        loginPage.checkUserIsNotLoggedInEmptyPassword();
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