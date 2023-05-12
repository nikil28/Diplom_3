import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    @Before
    public void configureDriver() {
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

}






