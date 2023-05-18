package pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HeaderPage extends BasePage{
    SelenideElement accountButton = $(byXpath("//p[text()='Личный Кабинет']/ancestor::a"));
    SelenideElement logo = $(byXpath("//div[@class='AppHeader_header__logo__2D0X2']/a"));
    SelenideElement constructorButton = $(byXpath("//p[text()='Конструктор']/ancestor::a[@href='/']"));
    private final String headerElementUrl = "/";
    @Step("Открыть главную страницу")
    public HeaderPage open() {
        Selenide.open(headerElementUrl);
        return this;
    }
    @Step("Нажать кнопку личный кабинет")
    public HeaderPage clickAccountButton() {
        accountButton.click();
        return this;
    }
    @Step("Нажать на логотип")
    public HeaderPage clickToLogo() {
        logo.click();
        return this;
    }
    @Step("Нажать на конструктор")
    public HeaderPage clickToConstructor() {
        constructorButton.click();
        return this;
    }
}
