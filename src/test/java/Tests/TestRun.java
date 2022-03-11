package Tests;

import PageObject.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestRun extends BasePage {

    @DisplayName("Закрыть ОПР и ППР")
    @Test
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

    public void close_all_OPR_tasks() {

        List<String> tasks_OPR_href = TasksPage
                .isOpened()
                .is_checked_monitoringRegions_region1_central()
                .get_all_OPR_links();

        tasks_OPR_href.forEach(
                href -> OneTaskPage.open(href)
                        .click_closeBtn()
                        .chose_type_of_closingWork()
                        .chose_reason_of_troubles()
                        .click_dialog_form_nextBtn()
                        .click_dialog_form_saveBtn()
                        .close_task_win());
    }

    public void close_all_PPR_tasks() {

        List <String> GroupTasks_href = TasksPage
                .isOpened()
                .is_checked_monitoringRegions_region1_central()
                .get_all_emtyGroups_links();

        GroupTasks_href.forEach(
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
