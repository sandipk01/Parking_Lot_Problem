package com.bridgelabz.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String getCurrentTimeInMinutes() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return sdf.format(cal.getTime());
    }

    public static String getCurrentTimeInHours() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(cal.getTime());
    }

    public static String getIncreaseTime(int hours, int minutes, int increaseMinutes) {
        int newMinutes = minutes + increaseMinutes;
        if (newMinutes >= 60) {
            hours++;
            newMinutes -= 60;
        }
        return hours + ":" + newMinutes;
    }

    public static int getDifferenceInMinutes(String time1, String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();
        return (int) ((difference / 1000) / 60);
    }

}
