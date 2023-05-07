package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement registrationHeader;

    @FindBy(how = How.XPATH, using = "//p[text()='Такой пользователь уже существует']")
    private SelenideElement userAlreadyExistErrorMessage;

    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordErrorMessage;

    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    private SelenideElement loginLink;

    @Step("ввести имя на странице регистрации")
    public RegistrationPage fillNameInput(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Ввести email на странице регистрации")
    public RegistrationPage fillEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Ввести пароль на странице регистрации")
    public RegistrationPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку регистрации на странице регистрации")
    public RegistrationPage clickRegistrationButton() {
        registerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на ссылку для входа на странице регистрации")
    public RegistrationPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return this;
    }

    @Step("Отобразится сообщение об ошибке Пользователь уже существует")
    public boolean isUserAlreadyExistErrorMessageDisplayed() {
        return userAlreadyExistErrorMessage.should(exist).isDisplayed();
    }

    @Step("Отобразится сообщение об ошибке Неверный пароль")
    public boolean isIncorrectPasswordErrorMessageDisplayed() {
        return incorrectPasswordErrorMessage.should(exist).isDisplayed();
    }

    @Step("Подождите, пока не откроется страница регистрации")
    public RegistrationPage registrationPageDisappear() {
        registrationHeader.should(disappear);
        return this;
    }

    @Step("Подождите, пока загрузится страница регистрации")
    public RegistrationPage registrationPageLoaded() {
        registrationHeader.shouldBe(visible);
        return this;
    }
}
