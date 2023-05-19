package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class RegistrationPage extends BasePage {
    private final SelenideElement nameInput = initInput("Имя");
    private final SelenideElement emailInput = initInput("Email");
    private final SelenideElement passwordInput = initInput("Пароль");
    private final SelenideElement registerButton = initButton("Зарегистрироваться");
    private final SelenideElement linkToSingIn = initLinkByText("Войти");
    private final SelenideElement errorIncorrectPassword = initErrorUnderInput("Некорректный пароль");

    private final String RegistrationPageUrl = "/register";
    @Step("Открыть страницу регистрация")
    public RegistrationPage open() {
        Selenide.open(RegistrationPageUrl);
        return this;
    }
    @Step("ввести имя на странице регистрации")
    public RegistrationPage enterName(String name) {
        nameInput.setValue(name);
        return this;
    }
    @Step("Ввести email на странице регистрации")
    public RegistrationPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }
    @Step("Ввести пароль на странице регистрации")
    public RegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }
    @Step("Нажать кнопку Зарегистрироваться")
    public RegistrationPage clickSingUpButton() {
        registerButton.click();
        return this;
    }
    @Step("Проверка ошибки Некорректный пароль")
    public RegistrationPage checkErrorUnderPasswordInput() {
        errorIncorrectPassword.shouldBe(Condition.visible);
        return this;
    }
    @Step("Нажать на кнопку Войти")
    public RegistrationPage clickSingIn() {
        linkToSingIn.click();
        return this;
    }
}
