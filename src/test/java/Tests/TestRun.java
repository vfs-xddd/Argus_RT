package Tests;

import PageObject.AuthorisationPage;
import PageObject.BasePage;
import PageObject.Data;
import PageObject.MainPage;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestRun extends BasePage {

    @Test
    public void Test1() {

        AuthorisationPage.open()
                .isOpened()
                .send_login()
                .send_password()
                .click_submitBtn()
                .isOpened()
                .click_repairing()
                .click_new_repair_problem()
                .select_typeRepairTask_asType_L()
                .select_groupInteractionRule_asType_adress()
                .add_correct_adress();


        System.out.println("++");
    }




}
