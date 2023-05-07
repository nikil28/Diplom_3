
import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    @Before
    public void configureDriver() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000;


        Configuration.browser = "chrome";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");


        //Configuration.holdBrowserOpen = true; // не закрывать браузер


        //Configuration.browser = "firefox"; // для запуска в firefox
        //Configuration.browserSize = "1366x768"; // поменять на другое разрешение


        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe"); // для запуска в яндекс браузере
        //var options = new ChromeOptions();
        //options.setBinary("C:\\Users\\Алексей Никитин\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

    }
}






