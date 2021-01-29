package com.teamV.imageUploadAWS.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getCurrentDate(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss. SSS");
        Date today = Calendar.getInstance()
                                .getTime();
        String dateToString = df.format(today);
        return dateToString;
    }
}
