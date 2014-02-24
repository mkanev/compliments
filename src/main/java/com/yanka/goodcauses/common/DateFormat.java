package com.yanka.goodcauses.common;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class DateFormat {

    private static final DateFormat INSTANCE = new DateFormat();
    private final TimeZone defaultTimeZone = TimeZone.getTimeZone("UTC");

    private DateFormat() {
    }

    public static DateFormat getInstance() {
        return INSTANCE;
    }

    public Calendar getCalendar() {
        return Calendar.getInstance(defaultTimeZone);
    }

    public Date now() {
        return getCalendar().getTime();
    }
}
