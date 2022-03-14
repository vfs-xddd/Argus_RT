package Tests;

import PageObject.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestRunNewTasks extends BasePage {

    public void Test1() {

        AuthorisationPage.open()
                .isOpened()
                .send_login()
                .send_password()
                .click_submitBtn()
                .isOpened()
                .click_menu_repairing()
                .click_new_repair_problem()
                .select_typeRepairTask_asType_L()
                .select_groupInteractionRule_asType_adress()
                .add_correct_adress()
                //***********************************************************************
                .click_calendar_startDate()
                .select_day()
                .click_calendar_startDate()
                .set_hour_slider_starDate()
                .click_calendar_startDate()
                .set_minute_slider_starEndtDate()
                //************************************************************************
                .click_calendar_endDate()
                .select_day()
                .click_calendar_endDate()
                .set_hour_slider_EndtDate();


        System.out.println("++");
    }

    public static void configTests() {
        final Map<String, String> new_props =
                Arrays.stream(new String[][] {
                        { "TARGET_DATE", "15.03.2022" },
                        { "groupTaskNum", "292551209" },
                        { "TARGET_TASKS_TIME", "08:00 - 17:00" },
                        { "repeats", "25" },
                }).collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));

       new_props.forEach(System::setProperty);
    }


    @BeforeAll
    public static void start() {
        configTests();
        String groupTaskNum = System.getProperty("groupTaskNum");  //номер вконце ссылки
        String href = "https://argus.south.rt.ru/argus/views/supportservice/grouprepairproblem/GroupRepairProblemView.xhtml?businessInteraction=GroupRepairProblem-" + groupTaskNum;

        AuthorisationPage.open()
                .isOpened()
                .send_login()
                .send_password()
                .click_submitBtn()
                .isOpened();

        GroupTaskPage.open_here(href);
    }

    @DisplayName("Создать новую задачу")
    @Test
    public void test2() {
        int tasks_num = Integer.parseInt(System.getProperty("repeats"));
        for (int i=1; i<=tasks_num; i++) {
            create_new_task();
            System.out.println(i);
        }
    }

    public void create_new_task() {
        BasePage.curentDate();
        GroupTaskPage.is_open()
                .tab_tasks()
                .click_newTaskBtn()
                .newTaskForm_select_categoryL()
                .newTaskForm_select_classOPR()
                .newTaskForm_input_OPRtext()
                .newTaskForm_select_Date()
                .newTaskForm_select_Time()
                .newTaskForm_click_allBtn()
                .newTaskForm_select_regions()
                .newTaskForm_click_saveBtn();
    }



}
