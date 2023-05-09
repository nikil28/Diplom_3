import api.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
@DisplayName("Вход в личный кабинет")
public class LoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void headerAccountButtonLoginWithValidDataTest() {
        open(MainPage.URL, HeaderPage.class).clickHeaderAccountButton();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void signInToAccountButtonLoginWithValidDataTest() {
        open(MainPage.URL, MainPage.class).clickSignInToAccountButton();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void linkLoginInRegistrationPageWithValidDataTest() {
        open(RegistrationPage.URL, RegistrationPage.class).clickLoginLink();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void linkLoginInForgotPasswordPageWithValidDataTest() {
        open(ForgotPasswordPage.URL, ForgotPasswordPage.class).clickLoginLink();
        checkLoginUserWithValidData();
    }

    @After
    @DisplayName("Перейти в профиль и выйти из системы, удалить пользователя, очистить файлы cookies")
    public void cleanDate() {
        page(HeaderPage.class).clickHeaderAccountButton();
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

    private void checkLoginUserWithValidData() {
        page(LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickLoginButton()
                .loginPageDisappear()
                .mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Залогиниться не удалось", MainPage.URL, currentURL);

        page(HeaderPage.class).clickHeaderAccountButton();
        String actualLogin = page(ProfilePage.class).getLoginInput();
        assertEquals("Логин не совпадает", validUserData.getEmail(), actualLogin);
    }
}
