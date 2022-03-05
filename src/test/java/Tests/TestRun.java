package Tests;

import PageObject.AuthorisationPage;
import PageObject.BasePage;
import PageObject.OneTaskPage;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestRun extends BasePage {

    @Test
    public void run(){

        close_usual_tasks();
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

    public void close_usual_tasks() {
        List <String> tasks_href =
            AuthorisationPage.open()
                    .isOpened()
                    .send_login()
                    .send_password()
                    .click_submitBtn()
                    .isOpened()
                    .click_menu_tasks()
                    .click_menu_tasks_listTasks()
                    .isOpened()
                    .click_monitoringRegions()
                    .expand_monitoring_regions_region1()
                    .select_monitoringRegions_region1_central()
                    .get_all_OPR_links();

        OneTaskPage.open(tasks_href.get(0))
                .click_closeBtn()
                .chose_type_of_closingWork()
                .chose_reason_of_troubles()
                .click_dialog_form_nextBtn()
                .click_dialog_form_saveBtn()
                .close_task_win()
                .isOpened();
    }




}
