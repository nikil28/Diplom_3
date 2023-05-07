import api.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@DisplayName("Профиль")
public class ProfileTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя, вход в систему и переход в профиль")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();

        open(LoginPage.URL, LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickLoginButton()
                .loginPageDisappear();

        page(HeaderPage.class).clickHeaderAccountButton();
    }

    @Test
    @DisplayName("Перейти из профиля в конструктор")
    public void navigateFromProfileToConstructorTest() {
        page(HeaderPage.class)
                .clickHeaderConstructorButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @Test
    @DisplayName("Переход из профиля на главную по логотипу")
    public void navigateFromProfileToMainPageLogoTest() {
        page(HeaderPage.class)
                .clickHeaderLogoButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @After
    @DisplayName("Переход в профиль, выход из системы, удаление пользователя и очистка файлов cookie")
    public void cleanDate() {
        page(HeaderPage.class)
                .clickHeaderAccountButton();
        page(ProfilePage.class)
                .profilePageLoaded()
                .clickLogoutButton()
                .profilePageDisappear();
        if (validUserData != null) {
            validUserData.deleteUserUsingAPI();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
