package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    SelenideElement singInAccountButton = initButton("Войти в аккаунт");
    String sauce = "Соусы";
    String bun = "Булки";
    String ingredient = "Начинки";

    SelenideElement sauceElement = $(byXpath(String.format("//span[text()='Соусы']", sauce)));
    SelenideElement bunElement = $(byXpath(String.format("//span[text()='Булки']", bun)));
    SelenideElement ingredientElement = $(byXpath(String.format("//span[text()='Начинки']", ingredient)));

    SelenideElement sauceTitle = initSecondTitle(sauce);
    SelenideElement ingredientTitle = initSecondTitle(ingredient);
    SelenideElement bunTitle = initSecondTitle(bun);

    String activeTab = "tab_tab_type_current";
    private final String mainPageUrl = "/";
    @Step("Открыть главную страницу")
    public MainPage open() {
        Selenide.open(mainPageUrl);
        return this;
    }

    public MainPage checkTitle() {
        initFirstTitle("Соберите бургер"); // Собери свой бургер
        return this;
    }
    @Step("Нажать кнопку войти в аккаунт")
    public MainPage clickSingInAccountButton() {
        singInAccountButton.click();
        return this;
    }
    @Step("Нажать кнопку соусы")
    public MainPage clickSauce() {
        sauceElement.click();
        return this;
    }
    @Step("Нажать кнопку начинки")
    public MainPage clickIngredient() {
        ingredientElement.click();
        return this;
    }
    @Step("Нажать кнопку булки")
    public MainPage clickBun() {
        bunElement.shouldBe(enabled).click();
        return this;
    }
    @Step("Проверить вкладку булки")
    public boolean checkActiveTabBun() {
        return bunElement.attr("class").contains(activeTab);
    }
    @Step("Проверить вкладку начинки")
    public boolean checkActiveTabIngredient() {
        return ingredientElement.attr("class").contains(activeTab);
    }
    @Step("Проверить вкладку соусы")
    public boolean checkActiveTabSauce() {
        return sauceElement.attr("class").contains(activeTab);
    }
    @Step("Проверка названия соусы")
    public MainPage checkSauceTitle() {
        sauceTitle.shouldBe(Condition.visible);
        return this;
    }
    @Step("Проверка названия булки")
    public MainPage checkBunTitle() {
        bunTitle.shouldBe(Condition.visible);
        return this;
    }
    @Step("Проверка названия начинки")
    public MainPage checkIngredientTitle() {
        ingredientTitle.shouldBe(Condition.visible);
        return this;
    }
}
