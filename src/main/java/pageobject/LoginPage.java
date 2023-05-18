package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class LoginPage extends BasePage{
    SelenideElement authPageTitle = initSecondTitle("Вход");
    SelenideElement emailInput = initInput("Email");
    SelenideElement passwordInput = initInput("Пароль");
    SelenideElement singInButton = initButton("Войти");
    SelenideElement errorIncorrectPassword = initErrorUnderInput("Некорректный пароль");
    private final String authPageUrl = "/login";
    @Step("Открыть страницу входа")
    public LoginPage open() {
        Selenide.open(authPageUrl);
        return this;
    }
    @Step("Ввести адрес электронной почты")
    public LoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }
    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }
    @Step("Нажать кнопку войти")
    public LoginPage clickButtonSingIn() {
        singInButton.click();
        return this;
    }
    @Step("Проверка ошибки ввода некорректного пароля")
    public LoginPage checkOutputIncorrectPasswordError() {
        errorIncorrectPassword.shouldBe(Condition.visible);
        return this;
    }
    @Step("Проверка названия входи, оно отображается")
    public LoginPage checkTitle() {
        authPageTitle.shouldBe(Condition.visible);
        return this;
    }
    @Step("Авторизация")
    public LoginPage auth(String email, String password) {
        this.open()
                .enterEmail(email)
                .enterPassword(password)
                .clickButtonSingIn();
        return this;
    }
}