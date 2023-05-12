package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement orderHeader;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement signInToAccountButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement tabBuns;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement tabSauces;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement tabFilling;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'current')]/span")
    private SelenideElement currentTab;

    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement headerFilling;

    @Step("Нажать кнопку войти в учетную запись")
    public MainPage clickSignInToAccountButton() {
        signInToAccountButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на вкладку булки")
    public MainPage clickTabBuns() {
        tabBuns.shouldBe(enabled).click();
        return this;
    }

    @Step("Нажать на вкладку соусы")
    public MainPage clickTabSauces() {
        tabSauces.shouldBe(enabled).click();
        return this;
    }

    @Step("Нажать на вкладку начинки")
    public MainPage clickTabFilling() {
        tabFilling.shouldBe(enabled).click();
        return this;
    }

    @Step("Получить название текущей вкладки")
    public String getCurrentTab() {
        return currentTab.getText();
    }

    @Step("Загружена главная страница")
    public MainPage mainPageLoaded() {
        orderHeader.shouldBe(visible);
        return this;
    }

    @Step("Главная страница исчезла")
    public MainPage mainPageDisappear() {
        orderHeader.should(disappear);
        return this;
    }

    @Step("Загружена ли главная страница")
    public boolean isMainPageLoaded() {
        return orderHeader.isDisplayed();
    }
}
