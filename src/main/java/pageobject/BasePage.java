package pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public SelenideElement initLinkByText(String linkText) {
        try {
            return $(byXpath(String.format("//a[contains(text(), '%s')]", linkText)));
        } catch (NoSuchElementException ex) {
            return $(byXpath(String.format("//p[contains(text(), '%s')]", linkText)));
        }
    }

    public SelenideElement initFirstTitle(String titleName) {
        return $(byXpath(String.format("//h1[text()='%s']", titleName)));
    }

    public SelenideElement initSecondTitle(String titleName) {
        return $(byXpath(String.format("//h2[text()='%s']", titleName)));
    }

    public SelenideElement initErrorUnderInput(String errorText) {
        return $(byXpath(String.format("//p[contains(@class, 'input__error') and text()='%s']", errorText)));
    }

    public SelenideElement initButton(String buttonName) {
        return $(byXpath(String.format("//button[text()='%s']", buttonName)));
    }

    public SelenideElement initInput(String placeholderName) {
        return $(byXpath(String.format("//label[text()='%s']/following-sibling::input", placeholderName)));
    }
}
