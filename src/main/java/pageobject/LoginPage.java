package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(className = "HeaderPage")
    private HeaderPage header;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement loginHeader;

    @Step("Заполнить поле email")
    public LoginPage fillEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Заполнить поле пароль")
    public LoginPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку войти")
    public LoginPage clickLoginButton() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    @Step("Страница входа в систему исчезла")
    public MainPage loginPageDisappear() {
        loginHeader.should(disappear);
        return page(MainPage.class);
    }

    @Step("Загружена страница входа в систему")
    public LoginPage loginPageLoaded() {
        loginHeader.shouldBe(visible);
        return this;
    }

    @Step("Загружена ли страница входа в систему")
    public boolean isLoginPageLoaded() {
        return loginHeader.isDisplayed();
    }
}