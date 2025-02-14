package org.sahni.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthSphereUtility {

    public static Date convertToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
