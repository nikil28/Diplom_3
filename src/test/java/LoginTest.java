import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.*;

@DisplayName("Тесты авторизации")
public class LoginTest extends BaseApi{
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();
    MainPage mainPage = new MainPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    HeaderPage headerPage = new HeaderPage();

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
