package tests.api;

import api.models.ProfileApiModel;

import api.steps.AuthApi;
import api.steps.TestStepsApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("API тесты на проверку данных профиля пользователя")
@Tag("API")
public class ProfileApiTest extends TestBaseApi {

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Профиль пользователя")
    @Story("API: Получение и проверка данных профиля пользователя")
    @DisplayName("Проверка данных профиля пользователя")
    public void testGetProfile() {
        AuthApi authApi = new AuthApi();
        Map<String, String> cookies = authApi.login(TestBaseApi.credentialsConfig.username(), TestBaseApi.credentialsConfig.password());

        TestStepsApi testStepsApi = new TestStepsApi();
        ProfileApiModel profile = testStepsApi.getProfile(cookies);


        assertEquals("darya.melgunova@gmail.com", profile.getEmail(), "Email не совпадает");
        assertEquals("Дарья Михайловна Мельгунова", profile.getFullName(), "ФИО не совпадает");
        assertEquals("FEMALE", profile.getGender(), "Пол не совпадает");
        assertNotNull(profile.getCompanyInfo(), "Информация о компании не должна быть null");
        assertEquals("02036457", profile.getCompanyInfo().getUid(), "UID компании не совпадает");
        assertEquals("ООО Судо", profile.getCompanyInfo().getName(), "Название компании не совпадает");
        assertEquals(false, profile.getCompanyInfo().getVip(), "VIP статус компании не совпадает");
    }
}
