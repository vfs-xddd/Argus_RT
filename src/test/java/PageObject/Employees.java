package PageObject;

import org.junit.jupiter.api.DisplayName;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Employees {

    public static int get_EmploeesNumPerDay(String targetDate) {
        int day;
        int numWorkersPerDay = 0;
        try {day = getDayOfWeek(targetDate);}
        catch (ParseException e) {return 0;}
        switch (day) {
            case 1: numWorkersPerDay = 4; break;  //воскр
            case 2: numWorkersPerDay = 9; break;  //понед
            case 3: numWorkersPerDay = 14; break;  //втор
            case 4: numWorkersPerDay = 14; break;  //средп
            case 5: numWorkersPerDay = 14; break;  //четверг
            case 6: numWorkersPerDay = 10; break;  //пятница
            case 7: numWorkersPerDay = 5; break;  //суббота
        }

        return numWorkersPerDay;
    }

    @DisplayName("1-воскр, ..., 7-суббота")
    private static int getDayOfWeek(String target_date) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(target_date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
