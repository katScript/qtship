package com.spring.app.helper.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatService {
    public static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String dateToString(Date date) {
        if (date == null)
            return null;

        return date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime()
                .format(DateTimeFormatter.ofPattern(FORMAT));
    }

    public static Date stringToDate(String date) {
        if (date == null)
            return null;

        try {
            return new SimpleDateFormat(FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
