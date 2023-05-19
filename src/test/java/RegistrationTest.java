import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;
import user.User;

import java.util.ArrayList;
import java.util.List;


@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseApi {
    private final List<User> userListForDelete = new ArrayList<>();
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();
    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password(7, 10);
    public static String invalidPassword = faker.internet().password(1, 4);
    public static String name = faker.name().fullName();


    @Test
    @DisplayName("Проверка регистрации нового пользователя с валидными данными")
    public void registerWithValidData() {
        registrationPage.open()
                .enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .clickSingUpButton();
        loginPage.checkTitle();
    }

    @Test
    @DisplayName("Попытка регистрации с паролем менее 6 символов")
    public void registerWithIncorrectPassword() {
        registrationPage.open()
                .enterName(name)
                .enterEmail(email)
                .enterPassword(invalidPassword)
                .clickSingUpButton()
                .checkErrorUnderPasswordInput();
        userListForDelete.add(new User(name, password, email));
        for (User user : userListForDelete) {
            if (user != null) {
                BaseApi.deleteUser();
            }
        }
    }
}
