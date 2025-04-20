package tests.api;

import api.models.ProfilePayload;
import api.steps.UserApiSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@DisplayName("API тесты на проверку данных профиля пользователя")
@Tag("API")
public class ProfileApiTest extends TestBaseApi {

    @Test
    @Owner("Мельгунова Дарья")
    @Feature("Профиль пользователя")
    @Story("API: Получение и проверка данных профиля пользователя")
    @DisplayName("Проверка данных профиля пользователя")
    public void testGetProfile() {

        ProfilePayload profile = new UserApiSteps().getProfile(cookies);


        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(profile.getEmail())
                    .as("Email не совпадает")
                    .isEqualTo("darya.melgunova@gmail.com");

            softly.assertThat(profile.getFullName())
                    .as("ФИО не совпадает")
                    .isEqualTo("Дарья Михайловна Мельгунова");

            softly.assertThat(profile.getGender())
                    .as("Пол не совпадает")
                    .isEqualTo("FEMALE");

            softly.assertThat(profile.getCompanyInfo())
                    .as("Информация о компании не должна быть null")
                    .isNotNull();

            softly.assertThat(profile.getCompanyInfo().getUid())
                    .as("UID компании не совпадает")
                    .isEqualTo("02036457");

            softly.assertThat(profile.getCompanyInfo().getName())
                    .as("Название компании не совпадает")
                    .isEqualTo("ООО Судо");

            softly.assertThat(profile.getCompanyInfo().getVip())
                    .as("VIP статус компании не совпадает")
                    .isFalse();
        });
    }
}
