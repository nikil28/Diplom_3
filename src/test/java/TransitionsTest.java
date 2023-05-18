import User.CreateAndAuthUserResponse;
import User.User;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@DisplayName("Тесты переходов")
public class TransitionsTest extends BaseTest{
    static Faker faker = new Faker(new Locale("ru"));
    LoginPage loginPage = new LoginPage();
    HeaderPage headerPage = new HeaderPage();
    ProfilePage profilePage = new ProfilePage();
    MainPage mainPage = new MainPage();

    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password(7,10);
    public static String invalidPassword = faker.internet().password(1,4);
    public static String name = faker.name().fullName();
    public static String tokenUser;
    @BeforeClass
    public static void createUser() {
        User user = new User(email, password, name);
        CreateAndAuthUserResponse responseUser =
                given().spec(specification)
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .body().as(CreateAndAuthUserResponse.class);
        tokenUser = responseUser.getAccessToken();
    }

    @AfterClass
    public static void deleteUser() {
        given().spec(specification)
                .header("Authorization", tokenUser)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
    }

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
