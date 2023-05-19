package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage {
    private final SelenideElement descriptionPage = $(byText("В этом разделе вы можете изменить свои персональные данные"));
    private final SelenideElement exitButton = initButton("Выход");
    private final String accountPageUrl = "/account/profile";

    public ProfilePage open() {
        Selenide.open(accountPageUrl);
        return this;
    }
    @Step("Найти описание страницы  этом разделе вы можете изменить свои персональные данные")
    public ProfilePage findDescriptionPage() {
        descriptionPage.shouldBe(Condition.visible);
        return this;
    }
    @Step("Нажать кнопку выход")
    public ProfilePage clickExitButton() {
        exitButton.click();
        return this;
    }
}
