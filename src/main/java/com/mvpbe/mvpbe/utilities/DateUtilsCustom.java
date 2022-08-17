package com.mvpbe.mvpbe.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilsCustom {

    public static Date dateXDaysAgo(Date start, int days){
        Calendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.add(Calendar.DAY_OF_MONTH, - days);
        Date daysAgo = cal.getTime();
        return daysAgo;
    }

}
