package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class GroupTaskPage extends BasePage{
    private static String default_win_handle;

    @FindBy(how = How.XPATH, using = "//label[@id='signal_form-available_transitions_label']")
    private static SelenideElement solutionField;

    @FindBy(how = How.XPATH, using = "//button[@id='signal_form-compleate']")
    private static SelenideElement closeBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='signal_process_dialog']")
    private static SelenideElement dialog_form;

    @FindBy(how = How.XPATH, using = "//label[@id='signal_process_dialog_form-close_problem_close_work_label']")
    private static SelenideElement field_choose_done_work;

    @FindBy(how = How.XPATH, using = "//label[@id='signal_process_dialog_form-close_problem_close_code_menu_label']")
    private static SelenideElement field_choose_reason_of_troubles;

    @FindBy(how = How.XPATH, using = "//button[@id='signal_process_dialog_form-close_problem_wizard_next']")
    private static SelenideElement dialog_form_nextBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='dialog-footer-buttons']/button/span[contains(text(), 'Сохранить')]")
    private static SelenideElement dialog_form_saveBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='gi_header-header_frame']/div[contains(text(),'Наряд закрыт')]")
    private static SelenideElement taskGroup_closed_check;

    @FindBy(how = How.XPATH, using = "//div[@id='group_interaction_info_form-tab_view']//a[contains(text(), 'Наряды')]")
    private static SelenideElement tab_tasks;   //вкладка Наряды

    @FindBy(how = How.XPATH, using = "//button[@id='group_interaction_info_form-tab_view-addOrder']")
    private static SelenideElement newTaskBtn;

    @FindBy(how = How.XPATH, using = "//label[@id='register_cable_order_form-code_gp_category_type_label']")
    private static SelenideElement newTaskForm_categoryTask;

    @FindBy(how = How.XPATH, using = "//label[@id='register_cable_order_form-gp_order_problem_type_list_label']")
    private static SelenideElement newTaskForm_classTask;

    @FindBy(how = How.XPATH, using = "//textarea[@id='register_cable_order_form-gp_order_damage']")
    private static SelenideElement newTaskForm_textarea;

    @FindBy(how = How.XPATH, using = "//input[@id='register_cable_order_form-visit_date_input_input']")
    private static SelenideElement newTaskForm_calendar;

    @FindBy(how = How.XPATH, using = "//label[@id='register_cable_order_form-plan_selector_label']")
    private static SelenideElement newTaskForm_time;

    @FindBy(how = How.XPATH, using = "//div[@id='register_cable_order_form-all_worksites_button']")
    private static SelenideElement newTaskForm_allBtn;  //кнопка Все

    @FindBy(how = How.XPATH, using = "//div[@id='register_cable_order_form-worksites']//input[contains(@id, 'worksite_ac_input')]")
    private static SelenideElement newTaskForm_regions;  //поле выбора цеха

    @FindBy(how = How.XPATH, using = "//div[@class='dialog-footer-buttons']/button/span[contains(text(), 'Сохранить')]")
    private static SelenideElement newTaskForm_saveBtn;

    private final String selected_time_xPath = "//div[@id='register_cable_order_form-plan_selector_panel']//tr[@data-label='?']";
    private final String selected_regions_xPath = "//table[contains(@class, 'ui-autocomplete-items')]//tr[contains(@data-item-label, '?')]";

    public static GroupTaskPage open(String href) {
        default_win_handle = driver.getWindowHandle();
        ((ChromeDriver) driver).executeScript("window.open()");
        String new_win_handle = (String) driver.getWindowHandles().toArray()[driver.getWindowHandles().size() - 1];
        driver.switchTo().window(new_win_handle);
        Selenide.open(href, OneTaskPage.class);
        is_open();
        return page(GroupTaskPage.class);
    }

    public static GroupTaskPage open_here(String href) {
        Selenide.open(href, OneTaskPage.class);
        is_open();
        return page(GroupTaskPage.class);
    }

    public static GroupTaskPage is_open() {
        Assertions.assertTrue(driver.getTitle().contains("ППР"));
        return page(GroupTaskPage.class);
    }

    public GroupTaskPage select_solutionField_close() {
        solutionField.shouldBe(Condition.visible).scrollIntoView(true);
        select_in_DropDownMenu(solutionField,"Закрыть");
        closeBtn.shouldBe(Condition.enabled);
        return page(this);
    }

    public GroupTaskPage click_closeBtn() {
        closeBtn.click();
        dialog_form.shouldBe(Condition.visible);
        return page(this);
    }

    public GroupTaskPage select_done_work() {
        select_in_DropDownMenu(field_choose_done_work, "ПР-Прочие - Ремонт");
        return page(this);
    }

    public GroupTaskPage select_reason_of_troubles() {
        select_in_DropDownMenu(field_choose_reason_of_troubles, "ПР-2-Предупредительный ремонт");
        return page(this);
    }

    public GroupTaskPage click_dialog_form_nextBtn() {
        dialog_form_nextBtn.scrollIntoView(true).shouldBe(Condition.visible).click();
        dialog_form_saveBtn.shouldBe(Condition.visible);
        return page(this);
    }

    public GroupTaskPage click_dialog_form_saveBtn() {
        dialog_form_saveBtn.shouldBe(Condition.visible).click();
        Assertions.assertTrue(taskGroup_closed_check.getText().contains("Наряд закрыт"));
        return page(this);
    }

    public TasksPage close_taskGroup_win() {
        Selenide.closeWindow();
        Selenide.switchTo().window(default_win_handle);
        return page(TasksPage.class);
    }

    public GroupTaskPage tab_tasks() {
        tab_tasks.shouldBe(Condition.visible).click();
        newTaskBtn.shouldBe(Condition.visible);     //$always visible, need to change
        return page(this);
    }

    public GroupTaskPage click_newTaskBtn() {
        newTaskBtn.shouldBe(Condition.visible).click();
        newTaskForm_categoryTask.shouldBe(Condition.visible);
        return page(this);
    }

    public GroupTaskPage newTaskForm_select_categoryL() {
        select_in_DropDownMenu(newTaskForm_categoryTask, "Л");
        newTaskForm_categoryTask.shouldBe(Condition.text("Л"));
        return page(this);
    }

    public GroupTaskPage newTaskForm_select_classOPR() {
        select_in_DropDownMenu(newTaskForm_classTask, "Охранно-предупредительные работы-Плановые работы по линии");
        newTaskForm_classTask.shouldBe(Condition.text("Охранно-предупредительные работы-Плановые работы по линии"));
        return page(this);
    }

    public GroupTaskPage newTaskForm_input_OPRtext() {
        newTaskForm_textarea.shouldBe(Condition.visible).click();
        newTaskForm_textarea.sendKeys(OPRTEXT);

        //$нет проверки на вставленный текст
        return page(this);
    }

    public GroupTaskPage newTaskForm_select_Date() {
        newTaskForm_calendar.shouldBe(Condition.visible).click();
        new DatePicker().select_day();  //выбирается TARGET_DATE
        newTaskForm_calendar.shouldHave(Condition.exactValue(TARGET_DATE));
        return page(this);
    }

    public GroupTaskPage newTaskForm_select_Time() {    //$возможно лучшая реализация
        String ready_xPath = selected_time_xPath.replace("?", TARGET_TASKS_TIME);
        for (int i=0; !newTaskForm_time.getText().equals(TARGET_TASKS_TIME); i++) {
            newTaskForm_time.shouldBe(Condition.visible).click();
            $x(ready_xPath).click();
            sleep(1000);
            if (i>5) throw new RuntimeException("Cant select time. i: " +i);
        }
        Assertions.assertEquals(TARGET_TASKS_TIME, newTaskForm_time.getText());
        return page(this);
    }

    public GroupTaskPage newTaskForm_click_allBtn() {
        newTaskForm_allBtn.shouldBe(Condition.visible).click();
        //$нет проверки
        return page(this);
    }

    public GroupTaskPage newTaskForm_select_regions() {
        newTaskForm_regions.shouldBe(Condition.visible).click();
        newTaskForm_regions.shouldBe(Condition.visible).clear();
        newTaskForm_regions.shouldBe(Condition.visible).sendKeys(REGION_NAME);
        String ready_xPath = selected_regions_xPath.replace("?", REGION_NAME);
        $x(ready_xPath).shouldBe(Condition.visible).click();
        //проверка не нужна, autocomlite строит варианты для клика только при наличии точного совпадения, сохранить форму с пустым полем невозможно
        return page(this);
    }

    public GroupTaskPage newTaskForm_click_saveBtn() {
        newTaskForm_saveBtn.shouldBe(Condition.visible).click();
        //$нет проверки
        return page(this);
    }
}
