import api.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@DisplayName("Регистрация")
public class RegistrationTest extends BaseTest {
    private final List<User> userListForDelete = new ArrayList<>();

    @Test
    @DisplayName("Проверка регистрации нового пользователя с валидными данными")
    public void newUserIsRegisteredWithValidDataTest() {
        User user = User.getRandomUserValidData();
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(user.getName())
                .fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();
        userListForDelete.add(user);
        String currentURL = webdriver().driver().url();
        assertEquals(LoginPage.URL, currentURL);
    }

    @Test
    @DisplayName("Повторная регистрация пользователя с существующей почтой")
    public void userIsNotRegisteredWithRepeatedEmailTest() {
        User userValid = User.getRandomUserValidData();
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userValid.getName())
                .fillEmailInput(userValid.getEmail())
                .fillPasswordInput(userValid.getPassword())
                .clickRegistrationButton();
        userListForDelete.add(userValid);

        User userNotValid = User.getRandomUserValidData();
        userNotValid.setEmail(userValid.getEmail());
        boolean isUserAlreadyExistErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickRegistrationButton()
                        .isUserAlreadyExistErrorMessageDisplayed();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Проверка, что новый пользователь не зарегистрирован без почты")
    public void newUserIsNotRegisteredWithoutEmailTest() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setEmail("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userNotValid.getName())
                .fillEmailInput(userNotValid.getEmail())
                .fillPasswordInput(userNotValid.getPassword())
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Проверка, что новый пользователь не зарегистрирован без имени")
    public void newUserIsNotRegisteredWithoutNameTest() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setName("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userNotValid.getName())
                .fillEmailInput(userNotValid.getEmail())
                .fillPasswordInput(userNotValid.getEmail())
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Проверка, что новый пользователь не зарегистрирован без пароля")
    public void newUserIsNotRegisteredWithoutPasswordTest() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setPassword("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput(User.getRandomValidEmail())
                .fillPasswordInput("")
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Попытка регистрации с паролем менее 6 символов")
    public void newUserIsNotRegisteredWithTooShortPasswordTest() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setPassword(User.getRandomTooShotPassword());
        boolean isIncorrectPasswordErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickRegistrationButton()
                        .isIncorrectPasswordErrorMessageDisplayed();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isIncorrectPasswordErrorMessageDisplayed);
    }

    @After
    @DisplayName("Удаление пользователя и очистка файлов cookies")
    public void cleanDate() {
        for (User user : userListForDelete) {
            if (user != null) {
                user.deleteUserUsingAPI();
            }
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
