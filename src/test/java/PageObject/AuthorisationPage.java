package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AuthorisationPage extends BasePage {
    private final static String start_page_url = "https://argus.south.rt.ru/argus/";

    @FindBy(how = How.XPATH, using = "//input[@id='login_form-username']")
    private SelenideElement login_field;

    @FindBy(how = How.XPATH, using = "//input[@id='login_form-password']")
    private SelenideElement password_field;

    @FindBy(how = How.XPATH, using = "//button[@id='login_form-submit']")
    private SelenideElement loginBtn;

    public AuthorisationPage isOpened() {
        login_field.shouldBe(Condition.visible);
        return page(this);
    }

    public static AuthorisationPage open() {
        Selenide.open(start_page_url, AuthorisationPage.class);
        return page(AuthorisationPage.class);
    }

    public AuthorisationPage send_login() {
        login_field.click();
        login_field.sendKeys(LOGIN);
        return page(this);
    }

    public AuthorisationPage send_password() {
        password_field.click();
        password_field.sendKeys(PASSWORD);
        return page(this);
    }

    public MainPage click_submitBtn() {
        loginBtn.click();
        return page(MainPage.class);
    }
}
