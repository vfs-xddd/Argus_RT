package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TasksPage extends BasePage{

    @FindBy(how = How.XPATH, using = "//span[@id='slcts-slct_acc-wrk_name']")
    private static SelenideElement myTasks;

    @FindBy(how = How.XPATH, using = "//div[@id='slcts-slct_acc-dsp_f_title']")
    private SelenideElement monitoring_regions;

    String monitoring_regions_region1_central_xPath = "//tbody[@id='slcts-slct_acc-dsp_f-tree_data']//span[text()='?']";
    private final SelenideElement monitoring_regions_region1_central = $x(monitoring_regions_region1_central_xPath.replace("?", REGION_NAME));

    @FindBy(how = How.XPATH, using = "//tr[@id='slcts-slct_acc-dsp_f-tree_node_0']//span[contains(@class, 'toggler')]")
    private SelenideElement monitoring_regions_region1_toggler;

    @FindBy(how = How.XPATH, using = "//form[@id='t_l_hdr_frm']/a")
    private SelenideElement top_panel_regionName;

    @FindBy(how = How.XPATH, using = "//tbody[@id='tbl_frm-tbl_data']/tr")
    private List <SelenideElement> tasksList_tr;

    @FindBy(how = How.XPATH, using = "//tbody[@id='tbl_frm-tbl_data']/tr//a")
    private List <SelenideElement> tasksList_a;

    @FindBy(how = How.XPATH, using = "//tbody[@id='tbl_frm-tbl_data']/parent::table")
    private SelenideElement table_body;
    private Boolean was_scrolled = false;


    @DisplayName("TaskPage checked open")
    public static TasksPage isOpened() {
        myTasks.shouldBe(Condition.visible);
        return page(TasksPage.class);
    }

    public TasksPage click_monitoringRegions() {
        monitoring_regions.shouldBe(Condition.visible).click();
        return page(this);
    }

    public TasksPage select_monitoringRegions_region1_central() {
        monitoring_regions_region1_central.shouldBe(Condition.visible).click();
        is_checked_monitoringRegions_region1_central();
        return page(this);
    }

    public TasksPage is_checked_monitoringRegions_region1_central() {
        top_panel_regionName.shouldHave(Condition.text(REGION_NAME));
        return page(this);
    }


    public TasksPage expand_monitoring_regions_region1() {
        monitoring_regions_region1_toggler.shouldBe(Condition.visible).click();
        return page(this);}

    @DisplayName("List of tr --> this.taskslist")
    private void scroll_and_get_full_tr_list() {
        int expected_tasks_num = Integer.parseInt(monitoring_regions_region1_central.parent().sibling(0).getText().split("/")[0]);
        while (this.tasksList_tr.size() < expected_tasks_num) {
            actions().sendKeys(table_body, Keys.END).click().perform();
        }
        this.was_scrolled = true;
        Assertions.assertEquals(expected_tasks_num, this.tasksList_tr.size());
    }

    @DisplayName("Все Охранно-предупредительные работы, кроме текущей даты")
    public List <String> get_all_OPR_links() {
        String oprs_name_starts_with = "Охранно-предупредительные";
        if (!was_scrolled) scroll_and_get_full_tr_list();
        String date = curentDate();

        return tasksList_a.stream()
                .filter(el -> el.getText().contains(oprs_name_starts_with))
                .filter(el -> !el.parent().parent().sibling(13).getText().substring(17).equals(date))
                .map(el -> el.getAttribute("href")).limit(2)
                .collect(Collectors.toList());
    }

    @DisplayName("Все пустые ППР и с текущей датой")
    public List <String> get_all_emtyGroups_links() {
        String oprs_name_starts_with = "ППР";
        if (!was_scrolled) scroll_and_get_full_tr_list();
        String date = curentDate();

        return tasksList_a.stream()
                .filter(el -> el.getText().contains(oprs_name_starts_with))
            //  .filter(el -> !el.parent().parent().sibling(13).getText().substring(17).equals(date))
                .map(el -> el.getAttribute("href")).limit(2)
                .collect(Collectors.toList());
    }

    @DisplayName("Все Охранно-предупредительные работы, кроме текущей даты. Все пустые ППР и с текущей датой")
    public List <List<String>> get_all_OPR_and_emtyGroups_links() {
        /**[0] список ОПР
         * [1] список ППР*/

        List <List<String>> res = new ArrayList<>();
        res.add(get_all_OPR_links());
        res.add(get_all_emtyGroups_links());
        return res;
    }

}
