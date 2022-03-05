package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;

public class OneTaskPage extends BasePage{
    private static String default_win_handle;

    @FindBy(how = How.XPATH, using = "//div[@id='header_frame']")
    private static SelenideElement header_frame;    //Информация об объекте большая строка слева верх

    @FindBy(how = How.XPATH, using = "//button[@id='signal_form-compleate']")
    private static SelenideElement closeBtn;

    @FindBy(how = How.XPATH, using = "//span[@id='support_service_order_form-information-for_update_after_global_edit_end_on_signal']")
    private static SelenideElement span_for_scrolling;

    @FindBy(how = How.XPATH, using = "//label[@id='signal_process_dialog_form-close_problem_close_work_label']")
    private static SelenideElement field_chose_type_work;

    @FindBy(how = How.XPATH, using = "//label[@id='signal_process_dialog_form-close_problem_close_code_menu_label']")
    private static SelenideElement field_chose_reason_of_troubles;

    @FindBy(how = How.XPATH, using = "//button[@id='signal_process_dialog_form-close_problem_wizard_next']")
    private static SelenideElement dialog_form_nextBtn;

    @FindBy(how = How.XPATH, using = "//span[@id='signal_process_dialog_title']")
    private static SelenideElement dialog_form_chose_worker_stage;

    @FindBy(how = How.XPATH, using = "//div[@class='dialog-footer-buttons']/button/span[contains(text(), 'Сохранить')]")
    private static SelenideElement dialog_form_saveBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='bi_header-header_frame']/div[contains(text(),'Наряд закрыт')]")
    private static SelenideElement task_closed_check;

    @FindBy(how = How.XPATH, using = "//div[@id='signal_process_dialog']")
    private static SelenideElement dialog_form;


    public static OneTaskPage open(String href) {
        default_win_handle = driver.getWindowHandle();
        ((ChromeDriver) driver).executeScript("window.open()");
        String new_win_handle = (String) driver.getWindowHandles().toArray()[driver.getWindowHandles().size() - 1];
        driver.switchTo().window(new_win_handle);
        Selenide.open(href, OneTaskPage.class);
        is_open();
    return page(OneTaskPage.class);
    }

    public static OneTaskPage is_open(){
        header_frame.shouldBe(Condition.visible);
        return page(OneTaskPage.class);
    }

    public OneTaskPage click_closeBtn () {
        closeBtn.scrollIntoView(true).shouldBe(Condition.visible).click();
        dialog_form.shouldBe(Condition.appear);
        return page(this);
    }

    public OneTaskPage chose_type_of_closingWork() {
        BasePage.select_in_DropDownMenu(field_chose_type_work, "ПР-Прочие - Ремонт");
        return page(this);
    }

    public OneTaskPage chose_reason_of_troubles() {
        BasePage.select_in_DropDownMenu(field_chose_reason_of_troubles, "ПР-2-Предупредительный ремонт");
        return page(this);
    }

    public OneTaskPage click_dialog_form_nextBtn() {
        dialog_form_nextBtn.scrollIntoView(true).shouldBe(Condition.visible).click();
        dialog_form_chose_worker_stage.shouldBe(Condition.appear);
        return page(this);
    }

    public OneTaskPage click_dialog_form_saveBtn () {
        dialog_form_saveBtn.scrollIntoView(true).shouldBe(Condition.visible).click();
        is_open();
        Assertions.assertEquals("Наряд закрыт", task_closed_check.getText());
        return page(this);
    }
    public TasksPage close_task_win() {
        Selenide.closeWindow();
        Selenide.switchTo().window(default_win_handle);
        return page(TasksPage.class);
    }
}


//
//    @DisplayName("Тип: Все")
//    private NewRepairProblemPage click_typeRepairTask() {
//        typeRepairTask.shouldBe(Condition.visible).click();
//        return page(this);
//    }
//
//    @DisplayName("Тип: Л")
//    public NewRepairProblemPage select_typeRepairTask_asType_L() {
//        click_typeRepairTask();
//        typeRepairTask_L.shouldBe(Condition.visible).click();
//        Assertions.assertTrue(typeRepairTask.getText().equals("Л"));
//        return page(this);
//}
