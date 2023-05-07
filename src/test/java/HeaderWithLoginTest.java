import api.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
@DisplayName("Header с входом в систему")
public class HeaderWithLoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создаем случайного пользователя и вход в систему")
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
        page(MainPage.class).mainPageLoaded();
    }

    @Test
    @DisplayName("Переход в личный кабинет с входом в систему")
    public void headerAccountButtonClickWithLoginTest() {
        page(HeaderPage.class).clickHeaderAccountButton();
        page(ProfilePage.class).profilePageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", ProfilePage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на конструктор с входом в систему")
    public void headerConstructorButtonClickWithLoginTest() {
        page(HeaderPage.class).clickHeaderConstructorButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на Лента заказов с входом в систему")
    public void headerFeedButtonClickWithLoginTest() {
        page(HeaderPage.class).clickHeaderFeedButton();
        page(FeedOrdersPage.class).feedOrderPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на логотип с входом в систему")
    public void headerLogoClickWithLoginTest() {
        page(HeaderPage.class).clickHeaderLogoButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }

    @After
    @DisplayName("Выход из системы, удаление пользователя, очистка файлов cookie")
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
}
