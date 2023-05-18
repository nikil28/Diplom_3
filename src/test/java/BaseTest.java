import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    public static RequestSpecification specification;

    @BeforeClass
    public static void configureDriver() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        specification = RestAssured.given();
        specification.baseUri("https://stellarburgers.nomoreparties.site");
        specification.header("Content-type", "application/json");

        System.setProperty("browser", "chrome"); // для запуска в chrome или firefox или yandex
        String browser = System.getProperty("browser");
        if(browser.equals("chrome")) {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
            // код для запуска тестов в Chrome
        } else if(browser.equals("firefox")) {
            Configuration.browser = "firefox";
            Configuration.browserSize = "1366x768";
            // код для запуска тестов в Firefox
        } else if (browser.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            var options = new ChromeOptions();
            options.setBinary("C:\\Users\\Алексей Никитин\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            // код для запуска тестов в Yandex
        }
        Configuration.timeout = 20000;

        Configuration.holdBrowserOpen = true; // не закрывать браузер

    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}






