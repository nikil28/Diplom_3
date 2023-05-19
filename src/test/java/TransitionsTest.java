import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

@DisplayName("Тесты переходов")
public class TransitionsTest extends BaseApi{
    LoginPage loginPage = new LoginPage();
    HeaderPage headerPage = new HeaderPage();
    ProfilePage profilePage = new ProfilePage();
    MainPage mainPage = new MainPage();
    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void transitionInAccountPage() {
        loginPage.auth(email, password);
        headerPage.clickAccountButton();
        profilePage.findDescriptionPage();
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void transitionInConstructorByClickToLogo() {
        headerPage.open().clickToLogo();
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор» ")
    public void transitionInConstructorByClickToConstructor() {
        headerPage.open()
                .clickToConstructor();
        mainPage.checkTitle();
    }

    @Test
    @DisplayName("Выход из аккаунта, по кнопке выйти в личном кабинете")
    public void exitOfAccount() {
        loginPage.auth(email, password);
        headerPage.clickAccountButton();
        profilePage.clickExitButton();
        loginPage.checkTitle();
    }

    @Test
    @DisplayName("Проверить нажатие вкладку Соусы")
    public void transitionToTabSauce() {
        mainPage.open()
                .clickSauce()
                .checkSauceTitle()
                .checkActiveTabSauce();
    }

    @Test
    @DisplayName("Проверить нажатие вкладку Начинки")
    public void transitionToTabIngredient() {
        mainPage.open();
        mainPage.clickIngredient()
                .checkIngredientTitle()
                .checkActiveTabIngredient();
    }

    @Test
    @DisplayName("Проверить нажатие вкладку Булки")
    public void transitionToTabBun() {
        mainPage.open();
        mainPage.clickIngredient();;
        mainPage.clickBun()
                .checkBunTitle()
                .checkActiveTabBun();
    }
}
