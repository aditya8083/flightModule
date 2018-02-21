package com.coviam.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class RandomDateGenerator {

    public String getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2017, 2018);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return formattedDate(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
    }

    public String formattedDate(String dateToFormat) {
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-M-dd");
        Date date = null;
        try {
            date = inputFormat.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (outputFormat.format(date));
    }

    public  int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
