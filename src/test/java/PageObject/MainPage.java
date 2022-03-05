package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']")
    private SelenideElement main_menu;

    @FindBy(how = How.XPATH, using = "//a[@id='mmf-create_group_repair_problem']")
    private SelenideElement create_repair_problem;

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']//span[text()='Повреждения']")
    private SelenideElement menu_repairing;

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']//span[contains(text(), 'Задачи')]")
    private SelenideElement menu_tasks;

    @FindBy(how = How.XPATH, using = "//div[@id='mmf-main_menu_bar']//span[contains(text(), 'Задачи')]/ancestor::li//span[contains(text(), 'Список задач')]")
    private SelenideElement menu_listTasks;


    @DisplayName("MainPage checked open")
    public MainPage isOpened() {
        main_menu.shouldBe(Condition.visible);
        return page(this);
    }

    public MainPage click_menu_repairing() {
        menu_repairing.shouldBe(Condition.visible).click();
        return page(this);
    }

    public MainPage click_menu_tasks() {
        menu_tasks.shouldBe(Condition.visible).click();
        return page(this);
    }

    public TasksPage click_menu_tasks_listTasks() {
        menu_listTasks.shouldBe(Condition.visible).click();
        Assertions.assertEquals("Список задач", driver.getTitle());
        return page(TasksPage.class);
    }


    public NewRepairProblemPage click_new_repair_problem() {
        create_repair_problem.shouldBe(Condition.visible).click();
        return page(NewRepairProblemPage.class);
    }

    public MainPage sleep(int ms) {
        Selenide.sleep(ms);
        return page(this);
    }


}
