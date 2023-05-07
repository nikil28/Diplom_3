import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.FeedOrdersPage;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
@DisplayName("Header без входа в систему")
public class HeaderWithoutLoginTest extends BaseTest {
    @Test
    @DisplayName("Переход в личный кабинет без входа в систему")
    public void headerAccountButtonClickWithoutLoginTest() {
        open(MainPage.URL, HeaderPage.class).clickHeaderAccountButton();
        page(LoginPage.class).loginPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", LoginPage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на конструктор без входа в систему")
    public void headerConstructorButtonClickWithoutLoginTest() {
        open(MainPage.URL, HeaderPage.class).clickHeaderConstructorButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на Лента заказов без входа в систему")
    public void headerFeedButtonClickWithoutLoginTest() {
        open(MainPage.URL, HeaderPage.class).clickHeaderFeedButton();
        page(FeedOrdersPage.class).feedOrderPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentURL);
    }

    @Test
    @DisplayName("Клик на логотип без входа в систему")
    public void headerLogoClickWithoutLoginTest() {
        open(MainPage.URL, HeaderPage.class).clickHeaderLogoButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }
}
