package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MainPage extends BasePage {

//    @FindBy(how = How.XPATH, using = "//h1[@id='logo']//following-sibling::ul/*/a")
//    private static List<SelenideElement> nav;

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']")
    private SelenideElement main_menu;

    @FindBy(how = How.XPATH, using = "//a[@id='mmf-create_group_repair_problem']")
    private SelenideElement create_repair_problem;

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']//span[text()='Повреждения']")
    private SelenideElement menu_repairing;

    @DisplayName("MainPage checked open")
    public MainPage isOpened() {
        main_menu.shouldBe(Condition.visible);
        return page(this);
    }

    public MainPage click_repairing() {
        menu_repairing.click();
        return page(this);
    }

    public NewRepairProblemPage click_new_repair_problem() {
        create_repair_problem.shouldBe(Condition.visible).click();
        return page(NewRepairProblemPage.class);
    }

    public MainPage sleep(int ms) {
        Selenide.sleep(ms);
        return page(this);
    }

//    public MainPage search_created_task() {
//        search_field.sendKeys(task_name);
//        if (search_field.exists()) {search_field.sendKeys(Keys.ENTER);}
//        return page(this);
//    }

}
