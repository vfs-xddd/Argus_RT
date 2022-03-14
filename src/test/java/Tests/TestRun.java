package Tests;

import PageObject.*;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRun extends BasePage {

    //@DisplayName("Закрыть ОПР и ППР")
    //@Test
    public void run(){
        close_all_OPR_tasks();
        close_all_PPR_tasks();
    }

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

    @BeforeAll
    public static void start() {
        AuthorisationPage.open()
                .isOpened()
                .send_login()
                .send_password()
                .click_submitBtn()
                .isOpened()
                .click_menu_tasks()
                .click_menu_tasks_listTasks()
                .click_monitoringRegions()
                .expand_monitoring_regions_region1()
                .select_monitoringRegions_region1_central();        //Выбран Центральный
    }

    @DisplayName("Закрыть все ОПР")
    @Test
    @Order(1)
    public void close_all_OPR_tasks() {

        List<String> tasks_OPR_href = TasksPage
                .isOpened()
                .is_checked_monitoringRegions_region1_central()
                .get_all_OPR_links();

        if (tasks_OPR_href.isEmpty()) {
            System.out.println("Нет ниодного пустого наряда ОПР");
            return;
        }

        tasks_OPR_href.forEach(
                href -> OneTaskPage.open(href)
                        .click_closeBtn()
                        .chose_type_of_closingWork()
                        .chose_reason_of_troubles()
                        .click_dialog_form_nextBtn()
                        .click_dialog_form_saveBtn()
                        .close_task_win());
    }


    @DisplayName("Закрыть все ППР")
    @Test
    @Order(2)
    public void close_all_PPR_tasks() {

        List <String> groupTasks_href = TasksPage
                .isOpened()
                .is_checked_monitoringRegions_region1_central()
                .get_all_emtyGroups_links();

        if (groupTasks_href.isEmpty()) {
            System.out.println("Нет ниодного пустого наряда ППР");
            return;
        }

        groupTasks_href.forEach(
                href -> GroupTaskPage.open(href)
                        .select_solutionField_close()
                        .click_closeBtn()
                        .select_done_work()
                        .select_reason_of_troubles()
                        .click_dialog_form_nextBtn()
                        .click_dialog_form_saveBtn()
                        .close_taskGroup_win());
    }





}
