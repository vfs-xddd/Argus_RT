package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class DatePicker extends BasePage {
    protected final String day = TARGET_DATE.split(Pattern.quote("."))[0];
    protected  final String month = TARGET_DATE.split(Pattern.quote("."))[1];
    protected  final String year = TARGET_DATE.split(Pattern.quote("."))[2];

    @FindBy(how = How.XPATH, using = "//input[@id= 'task_instance_plan_form-start_date_input_input']")
    private SelenideElement calendar_div_start_date;

    @FindBy(how = How.XPATH, using = "//input[@id= 'task_instance_plan_form-finish_date_input_input']")
    private SelenideElement calendar_div_end_date;

    @FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']//dd[@class='ui_tpicker_hour']/div/span[@class='ui-slider-handle ui-state-default ui-corner-all']")
    private SelenideElement hour_slider;

    @FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']//dd[@class='ui_tpicker_minute']/div/span[@class='ui-slider-handle ui-state-default ui-corner-all']")
    private SelenideElement minute_slider;


    @FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']//dd[@class='ui_tpicker_hour']/div")
    private SelenideElement hour_slider_div;

    private final String date_cell_xPath = "//table[@class='ui-datepicker-calendar']//a[text()= '?']";

    private void is_open() {
        calendar_div_start_date.shouldBe(Condition.visible);
        //better universal condition check mb?
    }


    public NewRepairProblemPage select_day() {
        //is_open();
        String curent_day = curentDate().split(Pattern.quote("."))[0];
        Assertions.assertTrue(Integer.parseInt(day) >= Integer.parseInt(curent_day));
        String ready_xPath = date_cell_xPath.replace("?", replace_dd_to_d_ifNeeds(day));
        $x(ready_xPath).shouldBe(Condition.visible).click();
        return page(NewRepairProblemPage.class);
    }

    @DisplayName("minute, hour")
    private void datepicker_set_time(String time_type, SelenideElement elem, int target_time) {
        is_open();
        int kH = 1;
        int kM = 4;
        int parts_in_hour_slider_line = 24; //hours
        int parts_in_minute_slider_line = 60;
        SelenideElement slider_div = elem.parent();
        int slider_div_start_xCord = slider_div.shouldBe(Condition.visible).getLocation().getX();      //same for minute
        int sliderWidth = slider_div.getSize().getWidth();
        int cords_for_part;
        int start_position_slider;
        int target_position_slider = 0;

        if (time_type.equals("minute")) {
            cords_for_part = sliderWidth / parts_in_minute_slider_line;
            start_position_slider = slider_div_start_xCord - cords_for_part * kM;
            target_position_slider = start_position_slider + cords_for_part * target_time;
        }
        if (time_type.equals("hour")) {
            cords_for_part = sliderWidth / parts_in_hour_slider_line;
            start_position_slider = slider_div_start_xCord - cords_for_part * kH;
            target_position_slider = start_position_slider + cords_for_part * target_time;
        }
        int curent_position_slider = elem.getLocation().getX();
        int xofSet_slider = target_position_slider - curent_position_slider;

        actions().moveToElement(elem)
                .dragAndDropBy(elem, xofSet_slider, 0).perform();
    }

    @DisplayName("minute, hour")
    private void datepicker_set_time_TEST(String time_type, SelenideElement elem, int target_time) {
        is_open();
        int kH = 1;
        int kM = 4;
        int parts_in_hour_slider_line = 24; //hours
        int parts_in_minute_slider_line = 60;
        SelenideElement slider_div = elem.parent();
        int slider_div_start_xCord = slider_div.shouldBe(Condition.visible).getLocation().getX();      //same for minute
        int sliderWidth = slider_div.getSize().getWidth();
        int cords_for_part;
        int start_position_slider;
        int target_position_slider = 0;
        //******************
        actions().moveToElement(elem).clickAndHold(elem).sendKeys(Keys.HOME).perform();
        if (time_type.equals("minute")) {
            cords_for_part = sliderWidth / parts_in_minute_slider_line;
            start_position_slider = slider_div_start_xCord - cords_for_part * kM;
            target_position_slider = start_position_slider + cords_for_part * target_time;
        }
        if (time_type.equals("hour")) {
            cords_for_part = sliderWidth / parts_in_hour_slider_line;
            start_position_slider = slider_div_start_xCord - cords_for_part * kH;
            target_position_slider = start_position_slider + cords_for_part * target_time;
        }
        int curent_position_slider = elem.getLocation().getX();
        int xofSet_slider = target_position_slider - curent_position_slider;

        actions().moveToElement(elem)
                .dragAndDropBy(elem, xofSet_slider, 0).perform();
    }

    private Boolean is_hours_correctAdded() {
        int actual_hour = Integer.parseInt(calendar_div_start_date.getValue().substring(11, 13));
        int expect_hour = TARGET_HOUR_START;
        return actual_hour == expect_hour;
    }

    public NewRepairProblemPage set_hour_slider_starDate() {
        datepicker_set_time("hour", hour_slider, TARGET_HOUR_START);
        int actual_hour = Integer.parseInt(calendar_div_start_date.getValue().substring(11, 13));
        int expect_hour = TARGET_HOUR_START;
        Assertions.assertEquals(expect_hour, actual_hour);

        return page(NewRepairProblemPage.class);
    }

    public NewRepairProblemPage set_hour_slider_EndtDate() {
        datepicker_set_time("hour", hour_slider, TARGET_HOUR_END);
        int actual_hour = Integer.parseInt(calendar_div_start_date.getValue().substring(11, 13));
        int expect_hour = TARGET_HOUR_START;
        Assertions.assertEquals(expect_hour, actual_hour);

        return page(NewRepairProblemPage.class);
    }


    public NewRepairProblemPage set_minute_slider_starEndtDate() {
        datepicker_set_time("minute", minute_slider, TARGET_MIN_START);
        int actual_min = Integer.parseInt(calendar_div_start_date.getValue().substring(14));
        int expect_min = TARGET_MIN_START;
        Assertions.assertEquals(expect_min, actual_min);
        return page(NewRepairProblemPage.class);
    }
}




