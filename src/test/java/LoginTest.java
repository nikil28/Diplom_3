import User.CreateAndAuthUserResponse;
import User.User;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pageobject.*;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@DisplayName("Тесты авторизации")
public class LoginTest extends BaseTest{
    static Faker faker = new Faker(new Locale("ru"));
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();
    MainPage mainPage = new MainPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    HeaderPage headerPage = new HeaderPage();
    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password(7,10);
    public static String invalidPassword = faker.internet().password(1,4);
    public static String name = faker.name().fullName();
    public static String tokenUser;

     @BeforeClass
    public static void createUser() {
        User user = new User(email, password, name);
        CreateAndAuthUserResponse responseUser =
                given().spec(specification)
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .body().as(CreateAndAuthUserResponse.class);
        tokenUser = responseUser.getAccessToken();
    }

    @AfterClass
    public static void deleteUser() {
        given().spec(specification)
                .header("Authorization", tokenUser)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void authWithValidData() {
        loginPage.auth(email, password);
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Авторизация с невалидными данными")
    public void authWithInvalidData() {
        loginPage.auth(email, invalidPassword)
                .checkOutputIncorrectPasswordError();
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти в аккаунт» на главной")
    public void authInMainPage() {
        mainPage.open().clickSingInAccountButton();
        loginPage.auth(email, password);
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    public void authInRegisterPage() {
        registrationPage.open().clickSingIn();
        loginPage.auth(email, password);
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Авторизация через кнопку «Личный кабинет»")
    public void authInAccount() {
        headerPage.open()
                .clickAccountButton();
        loginPage.auth(email, password);
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void authInForgotPasswordPage() {
        forgotPasswordPage.open().clickToLinkSingIn();
        loginPage.auth(email, password);
        mainPage.checkTitle();
    }
}
