import api.SuccessSignRs;
import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static org.junit.Assert.assertEquals;


public class SuccessfulRegistrationTest extends BaseTest {
    UserClient client = new UserClient();
    String email = User.getRandomValidEmail();
    String password = User.getRandomValidPassword();
    String accessToken;

    private final List<User> userListForDelete = new ArrayList<>();




    @Test
    @DisplayName("Проверка регистрации нового пользователя с валидными данными")
    public void successfulRegistration() {
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

        ValidatableResponse response = client.loginUser(new UserClient(email, password));
        accessToken = response
                .extract()
                .as(SuccessSignRs.class).getAccessToken();
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
