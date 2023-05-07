import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

@DisplayName("Тест конструктора")
public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Проверить нажатие вкладку Булки")
    public void clickTabBunsTest() {
        String expectedTab = "Булки";
        open(MainPage.URL, MainPage.class).clickTabSauces();
        page(MainPage.class).clickTabBuns();

        String currentTab = page(MainPage.class).getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверить нажатие вкладку Соусы")
    public void clickTabSaucesTest() {
        String expectedTab = "Соусы";
        String currentTab = open(MainPage.URL, MainPage.class)
                .clickTabSauces()
                .getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверить нажатие вкладку Начинки")
    public void clickTabFillingTest() {
        String expectedTab = "Начинки";
        String currentTab = open(MainPage.URL, MainPage.class)
                .clickTabFilling()
                .getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }
}