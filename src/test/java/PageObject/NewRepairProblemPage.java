package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Comparator;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class NewRepairProblemPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//label[@id='group_interaction_info_form-tab_view-code_category_type_label']")
    private SelenideElement typeRepairTask;

    @FindBy(how = How.XPATH, using = "//li[@data-label='Л']")
    private SelenideElement typeRepairTask_L;

    @FindBy(how = How.XPATH, using = "//label[@id='group_interaction_rule_frame_form-selected_group_problem_rule_type_label']")
    private SelenideElement groupInteractionRule;

    @FindBy(how = How.XPATH, using = "//li[@data-label='По зданию, типу и технологии услуги']")
    private SelenideElement groupInteractionRule_adress;

    @FindBy(how = How.XPATH, using = "//input[@id='group_interaction_rule_frame_form-building_rule_address-address_ac_input']")
    private SelenideElement adress_field;

    String adress_autocompliteVariants_xPath = "//div[@id='group_interaction_rule_frame_form-building_rule_address-address_ac_panel']//li[contains(@data-item-label, '?')]";

    @FindBy(how = How.XPATH, using = "//tbody[@id='group_interaction_info_form-tab_view-group_interaction_rule_table_data']//td[1]")
    private SelenideElement rulesTable_firstTd;

    @FindBy(how = How.XPATH, using = "//span[text()='Добавить']")
    private SelenideElement add_ruleBtn;

    @FindBy(how = How.XPATH, using = "//span[@class='ui-autocomplete-token-label']")
    private SelenideElement adress_field_span_when_selected_but_notAdded;


    @DisplayName("NewRepairProblemPage checked open")
    public NewRepairProblemPage isOpened() {
        typeRepairTask.shouldBe(Condition.visible);
        return page(this);
    }

    @DisplayName("Тип: Все")
    private NewRepairProblemPage click_typeRepairTask() {
        typeRepairTask.shouldBe(Condition.visible).click();
        return page(this);
    }

    @DisplayName("Тип: Л")
    public NewRepairProblemPage select_typeRepairTask_asType_L() {
        click_typeRepairTask();
        typeRepairTask_L.shouldBe(Condition.visible).click();
        typeRepairTask.getText().equals("Л");
        return page(this);
    }

    @DisplayName("Добавить правило: По региону, типу и технологии услуги")
    private NewRepairProblemPage click_groupInteractionRule() {
        groupInteractionRule.click();
        return page(this);
    }

    @DisplayName("Добавить правило: По зданию, типу и технологии услуги")
    public NewRepairProblemPage select_groupInteractionRule_asType_adress() {
        click_groupInteractionRule();
        groupInteractionRule_adress.scrollTo().shouldBe(Condition.visible).click();
        groupInteractionRule.getText().equals("По зданию, типу и технологии услуги");
        return page(this);
    }

    @DisplayName("Ввести корректный адрес с услугами")
    public NewRepairProblemPage add_correct_adress() {
        adress_field.scrollIntoView(true);

        for (int street_id=last_checked_street_id; street_id<STREET_LIST.size(); street_id++) {
            String street_name = STREET_LIST.get(street_id);

            int odd=0; int even=0; String adress; int adress_number=0;           //odd even - нечетные и четные
            for (int i = streets_map.get(street_name)+1;; i++) {
                int k=0;
                if (i%2==0) k = even*2-2;
                else k = odd*2-2;
                if (k<0) k=0;
                adress_number = i +k;
//                if (i%2==0) adress_number = i+even*2;
//                else adress_number = i+odd*2;
                adress = "Волгоград " + street_name + " " + adress_number;
                adress_field.sendKeys(adress);
                click_to_autocompliteAdress(street_name);
                click_add_ruleBtn();
                if (is_adress_adedd()) {save_street_last_idAdressNumber(street_id, i);
                    return page(this);
                }
                if (i%2==0) even++;
                else odd++;
                if (even==3 & odd==3) {
                    save_street_last_idAdressNumber(street_id, adress_number);
                    break;
                }
            }
        }


        return page(this);
    }

    private void click_to_autocompliteAdress (String street_name) {
        String attr_name = "data-item-label";
        String ready_xPath = adress_autocompliteVariants_xPath.replace("?", street_name);
        $x(ready_xPath).shouldBe(Condition.visible);
        $$x(ready_xPath).stream()
                .sorted(Comparator.comparingInt(el -> el.getAttribute(attr_name).length()))
                .collect(Collectors.toList())
                .get(0)
                .click();
        adress_field_span_when_selected_but_notAdded.shouldBe(Condition.appear);
    }

    private void click_add_ruleBtn() {
        add_ruleBtn.click();
        adress_field_span_when_selected_but_notAdded.shouldNotBe(Condition.visible);
    }

    @DisplayName("Проверяем что в таблице правил появились данные")
    private Boolean is_adress_adedd() {
    //ожидание
        return !rulesTable_firstTd.getText().equals("Нет объектов");
    }

    private void save_street_last_idAdressNumber(int street_id, int adress_number) {
        String street_name = STREET_LIST.get(street_id);
        last_checked_street_id = street_id;
        streets_map.put(street_name, adress_number);
    }

    public NewRepairProblemPage sleep(int ms) {
        Selenide.sleep(ms);
        return page(this);
    }

}
