package tests.api;

import api.auth.AuthApi;
import api.models.ProfileApiModel;
import api.steps.TestStepsApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API тесты на проверку данных профиля пользователя")
@Tag("API")
public class ProfileApiTest extends TestBaseApi {
    private final String username = "darya.melgunova@gmail.com";  // Замените на нужный логин
    private final String password = "BestLife2025";  // Замените на нужный пароль

    @Test
    public void testGetProfile() {
        AuthApi authApi = new AuthApi();
        authApi.login(username, password);
        Map<String, String> cookies = authApi.getCookies();

        TestStepsApi testStepsApi = new TestStepsApi();
        ProfileApiModel profile = testStepsApi.getProfile(cookies);

        assertThat(profile).isNotNull();
        assertThat(profile.getEmail()).isNotEmpty();
        assertThat(profile.getFullName()).isNotEmpty();
    }
}

