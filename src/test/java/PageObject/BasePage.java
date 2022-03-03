package PageObject;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasePage {

    protected final String LOGIN = "VLG_ANTONOV_V";
    protected final String PASSWORD = "Fynjyjd3#";
    protected  static WebDriver driver;
    protected HashMap<String, Integer> streets_map = Data.get_map_streets();
    protected int last_checked_street_id = 0;
    protected final ArrayList <String> STREET_LIST= Data.streets;
    protected String TARGET_DATE = "3.03.2022";
    protected int TARGET_HOUR = 8;
    protected int TARGET_MIN = 0;

    @BeforeAll
    public static void setupSettings() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
    }

//    @BeforeAll
//    static void prepare_steps() {
//        AuthorisationPage.open()
//                .isOpened()
//                .send_login()
//                .send_password()
//                .click_submitBtn()
//                .isOpened();
//    }
    public void select_in_dropDownList() {

    }


    @AfterAll
    public static void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
