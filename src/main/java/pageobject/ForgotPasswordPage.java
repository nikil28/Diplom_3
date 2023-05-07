package pageobject;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
public class ForgotPasswordPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Нажать на ссылку войти")
    public LoginPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return page(LoginPage.class);
    }

    @Step("Страница восстановления пароля исчезла")
    public ForgotPasswordPage forgotPasswordPageDisappear() {
        loginLink.should(disappear);
        return this;
    }

    @Step("Загружена страница восстановления пароля")
    public ForgotPasswordPage forgotPasswordPageLoaded() {
        loginLink.shouldBe(visible);
        return this;
    }

}
