import User.CreateAndAuthUserResponse;
import User.User;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseTest{
    static Faker faker = new Faker(new Locale("ru"));
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();

    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password(7,10);
    public static String invalidPassword = faker.internet().password(1,4);
    public static String name = faker.name().fullName();
    public static String tokenUser;

    @AfterClass
    public static void deleteUser() {
        User user = new User(email,password);
        CreateAndAuthUserResponse response = given().spec(specification)
                .body(user)
                .when()
                .post("/api/auth/login").as(CreateAndAuthUserResponse.class);
        tokenUser = response.getAccessToken();

        given().spec(specification)
                .header("Authorization", tokenUser)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
    }

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
    }
}
