package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement emailField = $("[placeholder='E-mail']");
    private final SelenideElement passwordField = $("[placeholder='Пароль']");
    private final SelenideElement loginButton = $("button[type=submit]");


    public LoginPage openPage() {
        open("/");
        $("a[href*='menu-g1-lichnyj_kabinet']").click();
        $(".auth-form-group").shouldBe(visible);
        return this;
    }


    public LoginPage enterEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }


}
