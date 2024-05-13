package tasks.services;

import java.time.LocalDate;
import java.util.Date;

public interface DateTimeService {

    Date getDateValueFromLocalDate(LocalDate localDate);

    Date getDateMergedWithTime(String time, Date noTimeDate);

    String getTimeOfTheDayFromDate(Date date);
}
