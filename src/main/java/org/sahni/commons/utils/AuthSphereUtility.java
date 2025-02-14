package org.sahni.commons.utils;

import java.sql.Date;

public class AuthSphereUtility {

    public static Date convertToDate(String date) {
        return Date.valueOf(date);
    }
}
