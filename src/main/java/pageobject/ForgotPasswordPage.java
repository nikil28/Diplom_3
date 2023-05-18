package pageobject;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class ForgotPasswordPage extends BasePage {
    SelenideElement linkToSingIn = initLinkByText("Войти");
    private final String forgotPasswordPageUrl = "/forgot-password";
    @Step("Открыть страницу восстановления пароля")
    public ForgotPasswordPage open() {
        Selenide.open(forgotPasswordPageUrl);
        return this;
    }
    @Step("Нажать кнопку войти")
    public ForgotPasswordPage clickToLinkSingIn() {
        linkToSingIn.click();
        return this;
    }

}
