package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class HeaderPage {
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'logo')]/a[@href='/']")
    private SelenideElement logoLink;

    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header')][@href='/']")
    private SelenideElement constructorLink;

    @FindBy(how = How.XPATH, using = "//a[@href='/feed']")
    private SelenideElement feedLink;

    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement accountLink;

    @Step("Нажать на кнопку с логотипом")
    public HeaderPage clickHeaderLogoButton() {
        logoLink.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку конструктор")
    public HeaderPage clickHeaderConstructorButton() {
        constructorLink.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку лента заказов")
    public HeaderPage clickHeaderFeedButton() {
        feedLink.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку личный кабинет")
    public HeaderPage clickHeaderAccountButton() {
        accountLink.shouldBe(visible).click();
        return this;
    }
}
