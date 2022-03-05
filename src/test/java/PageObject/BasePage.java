package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    protected final String LOGIN = "VLG_ANTONOV_V";
    protected final String PASSWORD = "Fynjyjd3#";
    protected  static WebDriver driver;
    protected HashMap<String, Integer> streets_map = Data.get_map_streets();
    protected int last_checked_street_id = 0;
    protected final ArrayList <String> STREET_LIST= Data.streets;
    protected String TARGET_DATE = "4.03.2022";     //dont forget to fix data to dd.mm.yyyy
    protected int TARGET_HOUR_START = 8;
    protected int TARGET_HOUR_END = 17;
    protected int TARGET_MIN_START = 0;
    protected int TARGET_MIN_END = 0;
    protected final String REGION_NAME = "Волгоград ЛКУ Центральный";

    @BeforeAll
    public static void setupSettings() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
    }

    protected static void select_in_DropDownMenu(SelenideElement first_field_toClick, String selected_text){
    String elem_xPath = "//li[@data-label='?']".replace("?", selected_text);
    first_field_toClick.shouldBe(Condition.visible).click();
    $x(elem_xPath).shouldBe(Condition.visible).click();
    }

    protected static String curentDate() {
        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);

    }

    @AfterAll
    public static void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
