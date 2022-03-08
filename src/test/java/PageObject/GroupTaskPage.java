package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

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


}
