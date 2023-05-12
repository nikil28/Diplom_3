package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;

public class ProfilePage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    @FindBy(how = How.XPATH, using = "//label[text()='Логин']/following-sibling::input")
    private SelenideElement loginInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("Получить данные для входа на странице профиля")
    public String getLoginInput() {
        return loginInput.shouldBe(visible).getValue();
    }

    @Step("Нажать кнопку выхода на странице профиля")
    public ProfilePage clickLogoutButton() {
        logoutButton.shouldBe(visible).click();
        return this;
    }

    @Step("Загружена страница профиля")
    public ProfilePage profilePageLoaded() {
        logoutButton.shouldBe(visible);
        return this;
    }

    @Step("Странница профиля исчезла")
    public ProfilePage profilePageDisappear() {
        logoutButton.should(disappear);
        return this;
    }
}
